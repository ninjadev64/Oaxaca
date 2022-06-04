package com.amansprojects.oaxaca.entitymetadata;

import com.amansprojects.oaxaca.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Living extends Entity {
    public String nameTag;
    public boolean alwaysShowNameTag;
    public float health;
    public int potionEffectColor;
    public boolean isPotionEffectAmbient;
    public int numberOfArrows;
    public boolean hasAi;

    public Living(boolean onFire, boolean crouched, boolean sprinting, boolean eating, boolean blocking, boolean drinking, boolean invisible, short air, boolean silent,
                  String nameTag, boolean alwaysShowNameTag, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows, boolean hasAi) {
        super(onFire, crouched, sprinting, eating, blocking, drinking, invisible, air, silent);
        this.nameTag = nameTag;
        this.alwaysShowNameTag = alwaysShowNameTag;
        this.health = health;
        this.potionEffectColor = potionEffectColor;
        this.isPotionEffectAmbient = isPotionEffectAmbient;
        this.numberOfArrows = numberOfArrows;
        this.hasAi = hasAi;
    }

    @Override
    public byte[] getFull() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        baos.writeBytes(super.getFull());
        dos.writeByte(MetadataFormat.STRING.getByte(2)); ByteUtils.writeStringToOutputStream(nameTag, baos);
        dos.writeByte(MetadataFormat.BYTE.getByte(3)); dos.writeBoolean(alwaysShowNameTag);
        dos.writeByte(MetadataFormat.FLOAT.getByte(6)); dos.writeFloat(health);
        dos.writeByte(MetadataFormat.INT.getByte(7)); dos.writeInt(potionEffectColor);
        dos.writeByte(MetadataFormat.BYTE.getByte(8)); dos.writeBoolean(isPotionEffectAmbient);
        dos.writeByte(MetadataFormat.BYTE.getByte(9)); dos.writeByte(numberOfArrows);
        dos.writeByte(MetadataFormat.BYTE.getByte(15)); dos.writeBoolean(hasAi);

        return baos.toByteArray();
    }
}
