package com.amansprojects.oaxaca;

import com.amansprojects.oaxaca.packets.outbound.ChatMessagePacketOut;

import java.io.IOException;
import java.net.SocketException;

import static com.amansprojects.oaxaca.Main.players;

public class API {
    public static void broadcastMessage(ChatObject object) {
        for (Player p : players) {
            try { new ChatMessagePacketOut(object, (byte) 0).send(p.socket); }
            catch (SocketException e) { /**/ }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static void removePlayer(Player p) throws IOException {
        players.remove(p);
        p.socket.close();

        ChatObject left = new ChatObject();
        left.text = p.username + " disconnected";
        left.color = "yellow";
        broadcastMessage(left);
    }
}
