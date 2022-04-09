package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Position;

import java.nio.ByteBuffer;

public class PlayerPositionPacket extends InboundPacket {
    public Position position;

    public PlayerPositionPacket(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(d); packetBuffer.position(1);
        position = new Position(packetBuffer.getDouble(), packetBuffer.getDouble(), packetBuffer.getDouble());
        // Logger.log("Received a Player Position packet with x " + position.x + ", feet y " + position.y + " and z " + position.z);
    }
}
