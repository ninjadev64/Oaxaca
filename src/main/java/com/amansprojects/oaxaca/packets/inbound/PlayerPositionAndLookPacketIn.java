package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Position;

import java.nio.ByteBuffer;

public class PlayerPositionAndLookPacketIn extends InboundPacket {
    public Position position;
    public final boolean onGround;

    public PlayerPositionAndLookPacketIn(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(d); packetBuffer.position(1);
        position = new Position(packetBuffer.getDouble(), packetBuffer.getDouble(), packetBuffer.getDouble(), packetBuffer.getFloat(), packetBuffer.getFloat());
        onGround = packetBuffer.get() >= 1;
    }
}
