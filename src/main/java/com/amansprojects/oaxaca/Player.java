package com.amansprojects.oaxaca;

import java.net.Socket;
import java.util.UUID;

public class Player {
    public final Socket socket;
    public final String username;
    public final UUID uuid;
    public final int eid;

    public Player(Socket socket, String username, UUID uuid, int eid) {
        this.socket = socket;
        this.username = username;
        this.uuid = uuid;
        this.eid = eid;
    }
}
