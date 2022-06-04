package com.amansprojects.oaxaca.entitymetadata;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Entity {
    public boolean onFire;
    public boolean crouched;
    public boolean sprinting;
    public boolean eating; public boolean blocking; public boolean drinking;
    public boolean invisible;

    public short air;
    public boolean silent;

    public Entity(boolean onFire, boolean crouched, boolean sprinting, boolean eating, boolean blocking, boolean drinking, boolean invisible, short air, boolean silent) {
        this.onFire = onFire;
        this.crouched = crouched;
        this.sprinting = sprinting;
        this.eating = eating;
        this.blocking = blocking;
        this.drinking = drinking;
        this.invisible = invisible;
        this.air = air;
        this.silent = silent;
    }

    public byte[] getFull() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        byte statusBitMask = 0;
        if (onFire) statusBitMask |= 0x01;
        if (crouched) statusBitMask |= 0x02;
        if (sprinting) statusBitMask |= 0x08;
        if (eating || blocking || drinking) statusBitMask |= 0x10;
        if (invisible) statusBitMask |= 0x20;
        dos.writeByte(MetadataFormat.BYTE.getByte(0)); dos.writeByte(statusBitMask);

        dos.writeByte(MetadataFormat.SHORT.getByte(1)); dos.writeShort(air);
        dos.writeByte(MetadataFormat.BYTE.getByte(4)); dos.writeBoolean(silent);
        return baos.toByteArray();
    }
}
