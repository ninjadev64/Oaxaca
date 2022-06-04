package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ConnectionState;
import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;
import com.amansprojects.oaxaca.Position;

import java.io.IOException;
import java.net.Socket;

public class PlayerPositionAndLookPacketOut implements OutboundPacket {
    public Position position;
    public ConnectionState state = ConnectionState.PLAY;

    public PlayerPositionAndLookPacketOut(double x, double y, double z, float yaw, float pitch) {
        this.position = new Position(x, y, z, yaw, pitch);
    }

    public PlayerPositionAndLookPacketOut(Position position) {
        this.position = position;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter((byte) 0x08);
        writer.writeDouble(position.x);
        writer.writeDouble(position.y);
        writer.writeDouble(position.z);
        writer.writeFloat(position.yaw);
        writer.writeFloat(position.pitch);
        writer.writeByte((byte) 0x00); // flags field is unused in this server implementation
        Main.socketWrite(socket, writer.finish());
    }
}
