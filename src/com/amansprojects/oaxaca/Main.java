package com.amansprojects.oaxaca;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Main {
    private static ServerSocket serverSocket = null;
    public static void main(String[] args) throws IOException {
        Logger.log("Starting Oaxaca server...");
        serverSocket = new ServerSocket(25565);
        connect();
    }

    public static void connect() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                if (packet[0] == 0) {
                    if (handshakeNextState == 0) {
                        handshakeNextState = new HandshakePacket(packet).nextState;
                    } else if (handshakeNextState == 1) {
                        new StatusPacket(packet);
                    } else if (handshakeNextState == 2) {
                        new LoginStartPacket(packet);
                    }
                }
            }

            // OutputStream output = socket.getOutputStream();
        }
    }
}
