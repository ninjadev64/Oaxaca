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

    public Living(boolean onFire, boolean crouched, boolean sprinting, boolean eating, boolean blocking, boolean drinking, boolean invisible, short air,
                  String nameTag, boolean alwaysShowNameTag, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows, boolean hasAi) {
        super(onFire, crouched, sprinting, eating, blocking, drinking, invisible, air);
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
        ByteUtils.writeStringToOutputStream(nameTag, baos);
        dos.writeByte(alwaysShowNameTag ? 1 : 0);
        dos.writeFloat(health);
        dos.writeInt(potionEffectColor);
        dos.writeByte(isPotionEffectAmbient ? 1 : 0);
        dos.writeByte(numberOfArrows);
        dos.writeByte(hasAi ? 1 : 0);

        return baos.toByteArray();
    }
}
