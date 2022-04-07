package com.amansprojects.oaxaca;

import java.util.Arrays;

public class StatusRequestPacket extends InboundPacket {
    public StatusRequestPacket(byte[] d) {
        super(d);
        Logger.log("Received a Status packet with data " + Arrays.toString(d));
    }
}
