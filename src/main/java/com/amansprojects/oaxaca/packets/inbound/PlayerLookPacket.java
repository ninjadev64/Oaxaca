package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

import java.nio.ByteBuffer;

public class PlayerLookPacket extends InboundPacket {
    public final float yaw;
    public final float pitch;

    public PlayerLookPacket(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(d); packetBuffer.position(1);
        yaw = packetBuffer.getFloat();
        pitch = packetBuffer.getFloat();
        Logger.log("Received a Player Look packet with yaw " + yaw + " and pitch " + pitch);
    }
}
