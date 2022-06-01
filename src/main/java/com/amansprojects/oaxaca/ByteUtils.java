package com.amansprojects.oaxaca;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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

    // Defective on Discord
    public static int checkVarIntSize(final int v) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int value = v;
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            bos.write(temp);
        } while (value != 0);
        return bos.size();
    }

    // my code now

    public static void writeString(String value, ByteBuffer out) {
        byte[] strBytes = value.getBytes(StandardCharsets.UTF_8);
        writeVarInt(strBytes.length, out);
        out.put(strBytes);
    }

    public static Position getPosition(long l) {
        double x = l >> 38;
        double y = (l >> 26) & 0xFFF;
        double z = l << 38 >> 38;
        return new Position(x, y, z);
    }

    public static float getAngle(float o360) {
        return ((360/o360)*256);
    }

    public static void writeVarIntToOutputStream(int value, ByteArrayOutputStream out) {
        ByteBuffer buffer = ByteBuffer.allocate(checkVarIntSize(value));
        writeVarInt(value, buffer);
        out.writeBytes(buffer.array());
    }
}
