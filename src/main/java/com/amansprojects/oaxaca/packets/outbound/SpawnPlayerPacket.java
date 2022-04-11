package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ByteUtils;
import com.amansprojects.oaxaca.PacketWriter;
import com.amansprojects.oaxaca.Player;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SpawnPlayerPacket implements OutboundPacket {
    public Player player;

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeVarInt(player.eid);
        writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(player.uuid.getMostSignificantBits()).array());
        writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(player.uuid.getLeastSignificantBits()).array());
        writer.writeInt((int) player.position.x);
        writer.writeInt((int) player.position.y);
        writer.writeInt((int) player.position.z);
        writer.writeInt((int) ByteUtils.getAngle(player.position.yaw));
        writer.writeInt((int) ByteUtils.getAngle(player.position.pitch));
        writer.writeShort((short) 0);
        // TODO: metadata
    }
}
