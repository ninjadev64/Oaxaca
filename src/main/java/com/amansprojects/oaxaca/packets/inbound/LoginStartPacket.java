package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.InboundPacket;
import com.amansprojects.oaxaca.Logger;

import java.util.Arrays;

public class LoginStartPacket extends InboundPacket {
    public String name;

    public LoginStartPacket(byte[] d) {
        super(d);
        name = new String(Arrays.copyOfRange(d, 2, d.length));
        Logger.log("Received a Login Start packet with name " + name);
    }
}
