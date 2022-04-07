package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.Logger;

public class HandshakePacket extends InboundPacket {
    public final int protocolVersion;
    public final String address;
    public final int port;
    public final int nextState;

    public HandshakePacket(byte[] d) {
        super(d);

        protocolVersion = Byte.toUnsignedInt(d[1]);

        byte[] addressByteArray = new byte[d[2]];
        if (d[2] - 1 >= 0) System.arraycopy(d, 3, addressByteArray, 0, d[2]);
        address = new String(addressByteArray);

        port = Byte.toUnsignedInt(d[3 + d[2]]) << 8 | Byte.toUnsignedInt(d[3 + d[2] + 1]);
        nextState = d[d.length - 1];

        Logger.log("Received a Handshake packet with protocol version " + protocolVersion + ", address " + address + ", port " + port + " and next state " + nextState);
    }
}
