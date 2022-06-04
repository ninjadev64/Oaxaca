package com.amansprojects.oaxaca;

import com.amansprojects.oaxaca.entitymetadata.Human;
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
    public Human metadata;

    public KeepAlivePacket lastSentKeepAlive;

    public Player(Socket socket, String username, UUID uuid, int eid, Gamemode gamemode, Position position) {
        this.socket = socket;
        this.username = username;
        this.uuid = uuid;
        this.eid = eid;

        this.gamemode = gamemode;
        this.position = position;

        this.metadata = new Human(
            false, // on fire
            false, // crouched
            false, // sprinting
            false, false, false, // eating, blocking, drinking
            false, // invisible
            (short) 10, // air
            false, // silent
            username, // name tag
            true, // always show name tag
            20, // health
            0, // potion effect colour
            false, // is potion effect ambient
            0, // number of arrows
            false, // has ai
            true, true, true, true, true, true, true,
            // cape enabled, jacket enabled, left sleeve enabled, right sleeve enabled, left pants leg enabled, right pants leg enabled, hat enabled
            0, // absorption hearts
            0 // score
        );
    }
}
