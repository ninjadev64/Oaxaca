package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

import java.nio.ByteBuffer;

public class PlayerPositionPacket extends InboundPacket {
    public final long x;
    public final long feetY;
    public final long z;

    public PlayerPositionPacket(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(data);
        packetBuffer.position(1);
        x = packetBuffer.getLong();
        feetY = packetBuffer.getLong();
        z = packetBuffer.getLong();
        Logger.log("Received a Player Position packet with x " + x + ", feet y " + feetY + " and z " + z);
    }
}
