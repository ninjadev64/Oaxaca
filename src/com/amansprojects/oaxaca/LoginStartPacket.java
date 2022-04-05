package com.amansprojects.oaxaca;

import java.util.Arrays;

public class LoginStartPacket extends Packet {
    public String name;

    public LoginStartPacket(byte[] d) {
        super(d);
        name = new String(Arrays.copyOfRange(d, 2, d.length));
        Logger.log("Received a Login Start packet with name " + name);
    }
}
