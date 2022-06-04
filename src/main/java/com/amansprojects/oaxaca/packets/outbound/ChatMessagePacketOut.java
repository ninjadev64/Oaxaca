package com.amansprojects.oaxaca.packets.outbound;

import com.amansprojects.oaxaca.ChatObject;
import com.amansprojects.oaxaca.Main;
import com.amansprojects.oaxaca.PacketWriter;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;

public class ChatMessagePacketOut implements OutboundPacket {
    public ChatObject contents;
    public byte position;

    public ChatMessagePacketOut(ChatObject contents, byte position) {
        this.contents = contents;
        this.position = position;
    }

    @Override
    public void send(Socket socket) throws IOException {
        PacketWriter writer = new PacketWriter((byte) 0x02);
        writer.writeString(new Gson().toJson(contents));
        writer.writeByte(position);
        Main.socketWrite(socket, writer.finish());
    }
}
