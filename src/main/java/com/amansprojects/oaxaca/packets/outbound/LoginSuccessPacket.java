package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LoginSuccessPacket implements OutboundPacket {
    final String name;
    public LoginSuccessPacket(String name) {
        this.name = name;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x02);

        writer.writeString(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8)).toString());
        writer.writeString(name);

        socket.getOutputStream().write(writer.finish());
    }
}
