package com.amansprojects.oaxaca;

import java.util.Arrays;

public class StatusPacket extends Packet {
    public StatusPacket(byte[] d) {
        super(d);
        System.out.println("Received a Status packet with data " + Arrays.toString(d));
    }
}
