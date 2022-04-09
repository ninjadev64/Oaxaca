package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ConnectionState;
import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class JoinGamePacket implements OutboundPacket {
    final int entityID;
    final byte gamemode;
    final boolean hardcore;
    final byte dimension;
    final byte difficulty;
    final byte maxPlayers;
    final String levelType;
    final boolean reducedDebugInfo;
    public ConnectionState state = ConnectionState.PLAY;

    public JoinGamePacket(int entityID, byte gamemode, boolean hardcore, byte dimension, byte difficulty, byte maxPlayers, String levelType, boolean reducedDebugInfo) {
        this.entityID = entityID;
        this.gamemode = gamemode;
        this.hardcore = hardcore;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter();
        writer.writeByte((byte) 0x01);
        if (!hardcore) {
            writer.writeByte(gamemode);
        } else {
            writer.writeByte((byte) (gamemode | 0x8));
        }
        writer.writeByteArray(ByteBuffer.allocate(4).putInt(entityID).array());
        writer.writeByte(dimension);
        writer.writeByte(difficulty);
        writer.writeByte(maxPlayers);
        writer.writeString(levelType);
        writer.writeBoolean(reducedDebugInfo);

        Main.socketWrite(socket, writer.finish());
    }
}
