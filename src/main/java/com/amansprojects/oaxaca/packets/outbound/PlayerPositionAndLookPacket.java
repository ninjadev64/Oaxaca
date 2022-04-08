package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ConnectionState;
import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;

public class PlayerPositionAndLookPacket implements OutboundPacket {
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;
    public ConnectionState state = ConnectionState.PLAY;

    public PlayerPositionAndLookPacket(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x08);
        writer.writeDouble(x);
        writer.writeDouble(y);
        writer.writeDouble(z);
        writer.writeFloat(yaw);
        writer.writeFloat(pitch);
        writer.writeByte((byte) 0x00); // flags field is unused in this server implementation
        socket.getOutputStream().write(writer.finish());
    }
}
