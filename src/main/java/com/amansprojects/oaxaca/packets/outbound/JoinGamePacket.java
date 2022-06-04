package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ConnectionState;
import com.amansprojects.oaxaca.Gamemode;
import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;

import java.io.IOException;
import java.net.Socket;

public class JoinGamePacket implements OutboundPacket {
    final int entityID;
    final Gamemode gamemode;
    final byte dimension;
    final byte difficulty;
    final byte maxPlayers;
    final String levelType;
    final boolean reducedDebugInfo;
    public ConnectionState state = ConnectionState.PLAY;

    public JoinGamePacket(int entityID, Gamemode gamemode, byte dimension, byte difficulty, byte maxPlayers, String levelType, boolean reducedDebugInfo) {
        this.entityID = entityID;
        this.gamemode = gamemode;
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
        writer.writeInt(entityID);
        writer.writeByte(gamemode.get());
        writer.writeByte(dimension);
        writer.writeByte(difficulty);
        writer.writeByte(maxPlayers);
        writer.writeString(levelType);
        writer.writeBoolean(reducedDebugInfo);

        Main.socketWrite(socket, writer.finish());
    }
}
