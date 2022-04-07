package com.amansprojects.oaxaca;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    private static ServerSocket serverSocket = null;
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        Logger.log("Starting Oaxaca server...");
        serverSocket = new ServerSocket(25565);

        new Thread(() -> {
            try { connect(); }
            catch (IOException e) { e.printStackTrace(); }
        }).start();
    }

    public static void connect() throws IOException {
        Socket socket = serverSocket.accept();
        new Thread(() -> {
            try { connect(); }
            catch (IOException e) { e.printStackTrace(); }
        }).start(); // Listen for a new connection on another thread

        InputStream input = socket.getInputStream();
        int handshakeNextState = 0;

        while (true) {
            try {
                int length = input.read(); // TODO: read this as a VarInt
                byte[] dat = new byte[length];
                input.read(dat);
                try {
                    if (dat[0] == 0x00) {
                        if (handshakeNextState == 0) {
                            handshakeNextState = new HandshakePacket(dat).nextState;
                        } else if (handshakeNextState == 1) {
                            new StatusRequestPacket(dat);
                            StatusResponsePacket statusResponsePacket = new StatusResponsePacket();

                            Yaml yaml = new Yaml(); // Yaml is not thread-safe so a new instance is needed for every connection
                            Map<String, Object> config = yaml.load(new FileInputStream("server.yml"));
                            statusResponsePacket.jsonResponse = new StatusResponsePacket.JsonResponse((int) config.get("maxPlayers"), 5, (ArrayList<String>) config.get("motd"));
                            String json = gson.toJson(statusResponsePacket.jsonResponse);
                            System.out.println(json);

                            PacketWriter writer = new PacketWriter();
                            writer.writeByte((byte) 0);
                            writer.writeString(json);

                            byte[] response = writer.finish();
                            for (byte b : response) System.out.print(Byte.toUnsignedInt(b) + " "); // debug
                            System.out.println(); // new line

                            socket.getOutputStream().write(response);
                        } else if (handshakeNextState == 2) {
                            new LoginStartPacket(dat);
                        }
                    } else if (dat[0] == 0x01) { // Ping packet, respond with pong
                        socket.getOutputStream().write(new byte[]{2, 1, dat[1]});
                    } else {
                        Logger.log("Unknown packet received with data " + Arrays.toString(dat));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Packet did not contain valid packet ID");
                    break;
                }
            } catch (IOException e) {
                break;
            } catch (NegativeArraySizeException e) {
                continue;
            }
        }

        // OutputStream output = socket.getOutputStream();
        socket.close();
    }
}
