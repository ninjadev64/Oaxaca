package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;
import com.amansprojects.oaxaca.Player;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerListItemPacket implements OutboundPacket {
    public int action = 0; // only add player for now
    public CopyOnWriteArrayList<Player> players;

    public PlayerListItemPacket(CopyOnWriteArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x38);
        writer.writeInt(action);
        writer.writeVarInt(players.size());
        for (Player p : players) {
            writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(p.uuid.getMostSignificantBits()).array());
            writer.writeByteArray(ByteBuffer.allocate(Long.BYTES).putLong(p.uuid.getLeastSignificantBits()).array());
            writer.writeString(p.username);
            writer.writeByte((byte) 0);
            writer.writeVarInt(p.gamemode.get());
            writer.writeVarInt(48);
            writer.writeBoolean(false);
        }
        Main.socketWrite(socket, writer.finish());
    }
}
