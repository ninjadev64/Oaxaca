package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

public class ChatMessagePacketIn extends InboundPacket {
    public final String string;

    public ChatMessagePacketIn(byte[] d) {
        super(d);

        byte[] stringBytes = new byte[d.length - 2];
        System.arraycopy(d, 2, stringBytes, 0, stringBytes.length);
        string = new String(stringBytes);

        Logger.log("Received a Chat Message packet with message " + string);
    }
}
