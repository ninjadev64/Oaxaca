package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

import java.util.Arrays;

public class UseEntityPacket extends InboundPacket {
    public UseEntityPacket(byte[] d) {
        super(d);
        Logger.log("Received a Use Entity packet with data " + Arrays.toString(d));
    }
}
