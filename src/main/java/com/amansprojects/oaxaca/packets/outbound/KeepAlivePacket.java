package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class KeepAlivePacket implements OutboundPacket {
    public final int data;
    public KeepAlivePacket() { data = ThreadLocalRandom.current().nextInt(129); }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter((byte) 0x00);
        writer.writeVarInt(data);
        Main.socketWrite(socket, writer.finish());
    }
}
