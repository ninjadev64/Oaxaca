package com.amansprojects.oaxaca;

public class HandshakePacket extends Packet {
    public int protocolVersion;
    public String address;
    public int port;
    public int nextState;
    public HandshakePacket(byte[] d) {
        super(d);

        protocolVersion = d[1];

        byte[] addressByteArray = new byte[d[2]];
        if (d[2] - 1 >= 0) System.arraycopy(d, 3, addressByteArray, 0, d[2]);
        address = new String(addressByteArray);

        port = Byte.toUnsignedInt(d[3 + d[2]]) << 8 | Byte.toUnsignedInt(d[3 + d[2] + 1]);
        nextState = d[d.length - 1];

        Logger.log("Received Handshake packet with protocol version " + protocolVersion + ", address " + address + ", port " + port + " and next state " + nextState);
    }
}
