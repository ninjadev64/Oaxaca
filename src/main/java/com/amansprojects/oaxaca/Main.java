package com.amansprojects.oaxaca;

import com.amansprojects.oaxaca.packets.inbound.*;
import com.amansprojects.oaxaca.packets.outbound.*;
import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private static ServerSocket serverSocket = null;
    public static int _lastEntityID = 0;
    public static final Gson gson = new Gson();
    public static CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<Player>();

    public static void main(String[] args) throws IOException {
        Logger.log("Starting Oaxaca server...");
        serverSocket = new ServerSocket(25565);

        new Thread(() -> {
            try { connect(); }
            catch (IOException e) { e.printStackTrace(); }
        }).start();

        TimerTask keepAliveTask = new TimerTask() {
            @Override
            public void run() {
                for (Player player : players) {
                    try {
                        if (player.socket.isClosed() || !player.socket.isConnected()) { API.removePlayer(player); continue; }
                        KeepAlivePacket packet = new KeepAlivePacket();
                        packet.send(player.socket);
                        player.lastSentKeepAlive = packet;
                    } catch (IOException e) {
                        try {
                            API.removePlayer(player);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        };
        Timer timer = new Timer("KeepAlive");
        timer.scheduleAtFixedRate(keepAliveTask, 0, 10000);
    }

    @SuppressWarnings("ConstantConditions")
    public static void connect() throws IOException {
        Socket socket = serverSocket.accept();
        new Thread(() -> {
            try { connect(); }
            catch (IOException e) { e.printStackTrace(); }
        }).start(); // Listen for a new connection on another thread

        Player player = null;
        InputStream input = socket.getInputStream();
        ConnectionState state = ConnectionState.HANDSHAKING;

        while (true) {
            try {
                int length = input.read(); // TODO: read this as a VarInt
                byte[] dat = new byte[length];
                input.read(dat);
                try {
                    switch (dat[0]) {
                    case 0x00 -> { // bad indentation but overall it's more readable without one extra level
                        switch (state) {
                            case HANDSHAKING -> { // Client sent a handshake
                                switch (new HandshakePacket(dat).nextState) {
                                    case 1 -> state = ConnectionState.STATUS;
                                    case 2 -> state = ConnectionState.LOGIN;
                                }
                            }
                            case STATUS -> { // Client sent a status request, respond with a status response
                                new StatusRequestPacket(dat);

                                Yaml yaml = new Yaml(); // Yaml is not thread-safe so a new instance is needed for every connection
                                Map<String, Object> config = yaml.load(new FileInputStream("server.yml"));
                                StatusResponsePacket statusResponsePacket = new StatusResponsePacket((int) config.get("maxPlayers"), 5, (ArrayList<String>) config.get("motd"));

                                statusResponsePacket.send(socket);
                            }
                            case LOGIN -> { // Client sent a Login Start packet, respond with a Login Success packet
                                LoginStartPacket loginStartPacket = new LoginStartPacket(dat);

                                _lastEntityID+=1;
                                player = new Player(
                                    socket,
                                    loginStartPacket.name,
                                    UUID.nameUUIDFromBytes(("OfflinePlayer:" + loginStartPacket.name).getBytes(StandardCharsets.UTF_8)),
                                    _lastEntityID,
                                    Gamemode.CREATIVE,
                                    new Position(0, 0, 0, 0, 0)
                                );
                                players.add(player);

                                new LoginSuccessPacket(player.username, player.uuid).send(socket);
                                new JoinGamePacket(player.eid, player.gamemode, (byte) 0, (byte) 0, (byte) 100, "flat", false).send(socket);
                                state = ConnectionState.PLAY;

                                // Now send them Player Position and Look to get them past "Downloading terrain"
                                new PlayerPositionAndLookPacketOut(player.position).send(socket);

                                // Update everyone's player list
                                for (Player p : players) new PlayerListItemPacket(players).send(p.socket);

                                // Send "joined the game" message
                                ChatObject joined = new ChatObject();
                                joined.text = player.username + " joined the game";
                                joined.color = "yellow";
                                API.broadcastMessage(joined);
                            }
                            case PLAY -> {
                                int data = ByteBuffer.wrap(dat).position(1).get();
                                if (player.lastSentKeepAlive.data != data) {
                                    player.socket.close(); // the client did not return the same keepalive data sent by the server
                                }
                                Logger.log("Received a Keep Alive Response packet with data " + data);
                            }
                        }
                    }
                    case (0x01) -> {
                        switch (state) {
                            case STATUS -> { // Ping packet, respond with pong
                                byte[] payload = new byte[dat.length - 1];
                                System.arraycopy(dat, 1, payload, 0, dat.length - 1);
                                byte[] metadata = new byte[]{(byte) (payload.length + 1), 1};

                                byte[] complete = new byte[payload.length + 2];
                                System.arraycopy(metadata, 0, complete, 0, metadata.length);
                                System.arraycopy(payload, 0, complete, metadata.length, payload.length);

                                socketWrite(socket, complete);
                                Logger.log("Received a ping packet with payload " + Arrays.toString(payload));
                                socket.close();
                            }
                            case PLAY -> {
                                ChatMessagePacketIn inPacket = new ChatMessagePacketIn(dat);

                                ChatObject object = new ChatObject();
                                object.text = "<" + player.username + "> " + inPacket.string;

                                API.broadcastMessage(object);
                            }
                        }
                    }
                    case (0x02) -> new UseEntityPacket(dat); // There are only Play server-bound packets with IDs >= 0x02
                    case (0x03) -> new PlayerPacket(dat);
                    case (0x04) -> player.position = new PlayerPositionPacket(dat).position;
                    case (0x05) -> {
                        PlayerLookPacket packet = new PlayerLookPacket(dat);
                        player.position.yaw = packet.yaw; player.position.pitch = packet.pitch;
                    }
                    case (0x06) -> player.position = new PlayerPositionAndLookPacketIn(dat).position;
                    case (0x07) -> new PlayerDiggingPacket(dat);
                    default -> Logger.log("Unknown packet received with data " + Arrays.toString(dat));
                }} catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Packet did not contain valid packet ID");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } catch (NegativeArraySizeException e) {
                continue;
            }
            if (socket.isClosed() || !socket.isConnected()) {
                if (player != null) API.removePlayer(player);
            }
        }

        // OutputStream output = socket.getOutputStream();
        socket.close();
    }

    public static synchronized void socketWrite(Socket socket, byte[] data) throws IOException {
        socket.getOutputStream().write(data);
    }
}
