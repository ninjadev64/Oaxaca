package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

import java.nio.ByteBuffer;

public class PlayerPositionPacket extends InboundPacket {
    public final double x;
    public final double feetY;
    public final double z;

    public PlayerPositionPacket(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(d); packetBuffer.position(1);
        x = packetBuffer.getDouble();
        feetY = packetBuffer.getDouble();
        z = packetBuffer.getDouble();
        Logger.log("Received a Player Position packet with x " + x + ", feet y " + feetY + " and z " + z);
    }
}
