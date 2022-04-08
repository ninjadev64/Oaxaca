package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ConnectionState;
import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class LoginSuccessPacket implements OutboundPacket {
    final String name;
    final UUID uuid;
    public LoginSuccessPacket(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }
    public ConnectionState state = ConnectionState.LOGIN;

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x02);

        writer.writeString(uuid.toString());
        writer.writeString(name);

        socket.getOutputStream().write(writer.finish());
    }
}
