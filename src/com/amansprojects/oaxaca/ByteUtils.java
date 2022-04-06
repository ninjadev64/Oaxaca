package com.amansprojects.oaxaca;

import java.nio.ByteBuffer;

public class ByteUtils {
    // https://wiki.vg/Protocol#VarInt_and_VarLong
    private static final int SEGMENT_BITS = 0x7F;
    private static final int CONTINUE_BIT = 0x80;
    public static int readVarInt(ByteBuffer buffer) {
        int value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = buffer.get();
            value |= (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 32) throw new RuntimeException("VarInt is too big");
        }

        return value;
    }
    public static long readVarLong(ByteBuffer buffer) {
        long value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = buffer.get();
            value |= (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 64) throw new RuntimeException("VarLong is too big");
        }

        return value;
    }
    public static void writeVarInt(int value, ByteBuffer out) {
        while (true) {
            if ((value & ~SEGMENT_BITS) == 0) {
                out.put((byte) value);
                return;
            }

            out.put((byte) ((value & SEGMENT_BITS) | CONTINUE_BIT));
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
        }
    }
    public static void writeVarLong(long value, ByteBuffer out) {
        while (true) {
            if ((value & ~((long) SEGMENT_BITS)) == 0) {
                out.put((byte) value);
                return;
            }

            out.put((byte) ((value & SEGMENT_BITS) | CONTINUE_BIT));
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
        }
    }
}
