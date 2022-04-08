package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class KeepAlivePacket implements OutboundPacket {
    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x00);
        writer.writeInt(ThreadLocalRandom.current().nextInt(129));

        socket.getOutputStream().write(writer.finish());
    }
}
