package com.amansprojects.oaxaca;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PacketWriter {
    public ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public DataOutputStream dos = new DataOutputStream(baos);

    public void writeByte(byte b) {
        baos.write(b);
    }

    public void writeByteArray(byte[] bytes) {
        try {
            baos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeVarInt(int i) {
        ByteBuffer buffer = ByteBuffer.allocate(ByteUtils.checkVarIntSize(i));
        ByteUtils.writeVarInt(i, buffer);
        writeByteArray(buffer.array());
    }

    public void writeString(String s) {
        int stringLength = s.getBytes(StandardCharsets.UTF_8).length;
        ByteBuffer buffer = ByteBuffer.allocate(ByteUtils.checkVarIntSize(stringLength) + stringLength);
        ByteUtils.writeString(s, buffer);
        writeByteArray(buffer.array());
    }

    public void writeBoolean(boolean b) {
        try { dos.writeBoolean(b); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void writeInt(int i) {
        try { dos.writeInt(i); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void writeDouble(double d) {
        try { dos.writeDouble(d); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void writeFloat(float f) {
        try { dos.writeFloat(f); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void writeShort(short s) {
        try { dos.writeShort(s); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public byte[] finish() {
        byte[] contents = baos.toByteArray();

        int packetLengthVarIntSize = ByteUtils.checkVarIntSize(contents.length);
        byte[] output = new byte[packetLengthVarIntSize + contents.length]; // The final packet array

        ByteBuffer lengthVarIntBuffer = ByteBuffer.allocate(packetLengthVarIntSize);
        ByteUtils.writeVarInt(contents.length, lengthVarIntBuffer);

        System.arraycopy(lengthVarIntBuffer.array(), 0, output, 0, packetLengthVarIntSize);
        System.arraycopy(contents, 0, output, packetLengthVarIntSize, contents.length);
        return output;
    }
}
