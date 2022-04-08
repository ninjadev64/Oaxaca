package com.amansprojects.oaxaca.packets.inbound;

public class PlayerPacket extends InboundPacket {
    public PlayerPacket(byte[] d) {
        super(d);
        // Logger.log("Received Player packet with data " + Arrays.toString(d));
    }
}
