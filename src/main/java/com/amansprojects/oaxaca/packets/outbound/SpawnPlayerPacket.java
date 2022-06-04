package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ByteUtils;
import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;
import com.amansprojects.oaxaca.Player;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SpawnPlayerPacket implements OutboundPacket {
    public Player player;

    public SpawnPlayerPacket(Player player) {
        this.player = player;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter((byte) 0x0C);

        writer.writeVarInt(player.eid);
        writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(player.uuid.getMostSignificantBits()).array());
        writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(player.uuid.getLeastSignificantBits()).array());
        writer.writeInt((int)(player.position.x * 32));
        writer.writeInt((int)(player.position.y * 32));
        writer.writeInt((int)(player.position.z * 32));
        writer.writeByte((byte) ByteUtils.getAngle(player.position.yaw));
        writer.writeByte((byte) ByteUtils.getAngle(player.position.pitch));
        writer.writeShort((short) 0);

        writer.writeByteArray(player.metadata.getFull());
        writer.writeByte((byte) 127);

        Main.socketWrite(socket, writer.finish());
    }
}
