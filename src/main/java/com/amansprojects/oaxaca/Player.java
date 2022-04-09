package com.amansprojects.oaxaca;

import com.amansprojects.oaxaca.packets.outbound.KeepAlivePacket;

import java.net.Socket;
import java.util.UUID;

public class Player {
    public final Socket socket;
    public final String username;
    public final UUID uuid;
    public final int eid;

    public Gamemode gamemode;
    public Position position;

    public KeepAlivePacket lastSentKeepAlive;

    public Player(Socket socket, String username, UUID uuid, int eid, Gamemode gamemode, Position position) {
        this.socket = socket;
        this.username = username;
        this.uuid = uuid;
        this.eid = eid;

        this.gamemode = gamemode;
        this.position = position;
    }
}
