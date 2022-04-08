package com.amansprojects.oaxaca.packets.inbound;

import com.amansprojects.oaxaca.ByteUtils;
import com.amansprojects.oaxaca.Logger;
import com.amansprojects.oaxaca.Position;

import java.nio.ByteBuffer;
import java.util.Objects;

public class PlayerDiggingPacket extends InboundPacket {
    public final DiggingStatus status;
    public final Position position;
    public final byte face;

    public PlayerDiggingPacket(byte[] d) {
        super(d);
        ByteBuffer packetBuffer = ByteBuffer.wrap(d); packetBuffer.position(1);
        status = DiggingStatus.valueOfCode(packetBuffer.get());
        position = ByteUtils.getPosition(packetBuffer.getLong());
        face = packetBuffer.get();
        switch (Objects.requireNonNull(status)) {
            case STARTED -> Logger.log("Received a Start Digging packet at position " + position);
            case CANCELLED -> Logger.log("Received a Cancel Digging packet at position " + position);
            case FINISHED -> Logger.log("Received a Finish Digging packet at position " + position);
            case DROP_ITEM_STACK -> Logger.log("Received a Drop Item Stack packet");
            case DROP_ITEM -> Logger.log("Received a Drop Item packet");
            case SHOOT_ARROW_OR_FINISH_EATING -> Logger.log("Received a Shoot Arrow / Finish Eating packet");
        }
    }

    public static enum DiggingStatus {
        STARTED(0),
        CANCELLED(1),
        FINISHED(2),
        DROP_ITEM_STACK(3),
        DROP_ITEM(4),
        SHOOT_ARROW_OR_FINISH_EATING(5);

        public final int statusCode;
        DiggingStatus(int statusCode) {
            this.statusCode = statusCode;
        }
        public static DiggingStatus valueOfCode(int code) {
            for (DiggingStatus e : values()) if (e.statusCode == code) return e;
            return null;
        }
    }
}
