package com.amansprojects.oaxaca.packets.inbound;

import java.nio.ByteBuffer;

public class PlayerPositionAndLookPacketIn extends InboundPacket {
    public final double x;
    public final double y;
    public final double z;
    public final float yaw;
    public final float pitch;
    public final boolean onGround;

    public PlayerPositionAndLookPacketIn(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(d); packetBuffer.position(1);
        x = packetBuffer.getDouble();
        y = packetBuffer.getDouble();
        z = packetBuffer.getDouble();
        yaw = packetBuffer.getFloat();
        pitch = packetBuffer.getFloat();
        byte oG = packetBuffer.get();
        onGround = oG >= 1;
    }
}
