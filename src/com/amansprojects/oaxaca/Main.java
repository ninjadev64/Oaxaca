package com.amansprojects.oaxaca;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static ServerSocket serverSocket = null;
    public static void main(String[] args) throws IOException {
        Logger.log("Starting Oaxaca server...");
        serverSocket = new ServerSocket(25565);
        new Thread(() -> {
            try { connect(); }
            catch (IOException e) { e.printStackTrace(); }
        }).start();
    }

    public static void connect() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try { connect(); }
                catch (IOException e) { e.printStackTrace(); }
            }).start(); // Listen for a new connection on another thread

            InputStream input = socket.getInputStream();
            ByteBuffer buffer = ByteBuffer.wrap(input.readAllBytes());
            int handshakeNextState = 0;

            ArrayList<byte[]> packets = new ArrayList<byte[]>();
            while (true) {
                int originalBufferIndex = buffer.position();
                try {
                    int length = buffer.get();
                    byte[] dat = new byte[length];
                    buffer.get(dat, 0, length);
                    try {
                        int packetId = dat[0];
                        packets.add(dat);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Packet did not contain valid packet ID");
                        break;
                    }
                } catch (BufferUnderflowException | NegativeArraySizeException e) {
                    buffer.position(originalBufferIndex);
                    break;
                }
            }

            for (byte[] packet : packets) {
                if (packet[0] == 0x00) {
                    if (handshakeNextState == 0) {
                        handshakeNextState = new HandshakePacket(packet).nextState;
                    } else if (handshakeNextState == 1) {
                        new StatusPacket(packet);

                        byte[] json = """
{
    "version": {
        "name": "1.8.7",
        "protocol": 47
    },
    "players": {
        "max": 100,
        "online": 5
    },
    "description": {
        "text": "Hello world"
    }
}""".getBytes();
                        ByteBuffer dataLengthOutBuffer = ByteBuffer.allocate(Math.round((float)(json.length + 1)/128)); // 128 is the max size of a single byte VarInt
                        ByteUtils.writeVarInt(json.length + 1, dataLengthOutBuffer);  // Write the data length to a VarInt as it can be larger than 128

                        byte[] dataLengthVarInt = dataLengthOutBuffer.array(); // Get the VarInt as it's multiple bytes
                        byte[] metadata = new byte[dataLengthVarInt.length + 1]; // Create an array to hold the data length and packet ID
                        System.arraycopy(dataLengthVarInt, 0, metadata, 0, dataLengthVarInt.length); // Merge the data length and packet ID
                        metadata[metadata.length - 1] = 0;

                        byte[] complete = new byte[json.length + metadata.length];
                        System.arraycopy(metadata, 0, complete, 0, metadata.length);
                        System.arraycopy(json, 0, complete, metadata.length, json.length);

                        socket.getOutputStream().write(complete);

                        for (byte b : complete) System.out.print(b + " "); // debug
                    } else if (handshakeNextState == 2) {
                        new LoginStartPacket(packet);
                    }
                } else if (packet[0] == 0x01) { // Ping packet, respond with pong
                    socket.getOutputStream().write(new byte[]{2, 1, packet[1]});
                } else {
                    Logger.log("Unknown packet received with data " + Arrays.toString(packet));
                }
            }

            // OutputStream output = socket.getOutputStream();
            socket.close();
        }
    }
}
