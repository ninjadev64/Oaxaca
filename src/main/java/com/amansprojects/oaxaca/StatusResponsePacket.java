package com.amansprojects.oaxaca;

import java.util.ArrayList;

public class StatusResponsePacket {
    public JsonResponse jsonResponse;
    public static class JsonResponse {
        public static class version {
            public String name = "Oaxaca 1.8.8";
            public int protocol = 47;
        }
        public static class players {
            public int max;
            public int online;
            public players(int m, int o) { max = m; online = o; };
        }
        public static class description {
            public String text;
            public description(ArrayList<String> description) {
                text = String.join("\n", description);
            }
        }

        public version version;
        public players players;
        public description description;
        public JsonResponse(int maxPlayers, int onlinePlayers, ArrayList<String> description) {
            version = new version();
            players = new players(maxPlayers, onlinePlayers);
            this.description = new description(description);
        }
    }
}
