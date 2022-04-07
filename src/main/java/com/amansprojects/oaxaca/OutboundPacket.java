package com.amansprojects.oaxaca;

import java.io.IOException;
import java.net.Socket;

public interface OutboundPacket {
    void send(Socket socket) throws IOException;
}
