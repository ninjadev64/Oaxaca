package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

import java.util.Arrays;

public class ChatMessagePacket extends InboundPacket {
    public ChatMessagePacket(byte[] d) {
        super(d);
        Logger.log("Received a Chat Message packet with data " + Arrays.toString(d));
    }
}
