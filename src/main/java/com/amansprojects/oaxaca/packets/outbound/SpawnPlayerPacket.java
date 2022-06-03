package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ByteUtils;
import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;
import com.amansprojects.oaxaca.Player;
import com.amansprojects.oaxaca.entitymetadata.Human;

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
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x0C);

        writer.writeVarInt(player.eid);
        writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(player.uuid.getMostSignificantBits()).array());
        writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(player.uuid.getLeastSignificantBits()).array());
        writer.writeInt((int) player.position.x);
        writer.writeInt((int) player.position.y);
        writer.writeInt((int) player.position.z);
        writer.writeInt((int) ByteUtils.getAngle(player.position.yaw));
        writer.writeInt((int) ByteUtils.getAngle(player.position.pitch));
        writer.writeShort((short) 0);

        Human human = new Human(false, false, false, false, false, false, false, (short) 10, false, player.username, false, 20, 0, false, 0, false, true, true, true, true, true, true, true, 0f, 10);
        writer.writeByteArray(human.getFull());

        Main.socketWrite(socket, writer.finish());
    }
}
