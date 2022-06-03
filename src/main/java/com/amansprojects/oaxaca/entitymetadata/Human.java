package com.amansprojects.oaxaca.entitymetadata;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Human extends Living {
    public boolean capeEnabled = false;
    public boolean jacketEnabled = false;
    public boolean leftSleeveEnabled = false;
    public boolean rightSleeveEnabled = false;
    public boolean leftPantsLegEnabled = false;
    public boolean rightPantsLegEnabled = false;
    public boolean hatEnabled = false;

    public float absorptionHearts = 0;
    public int score = 0;

    public Human(boolean onFire, boolean crouched, boolean sprinting, boolean eating, boolean blocking, boolean drinking, boolean invisible, short air,
                 String nameTag, boolean alwaysShowNameTag, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows, boolean hasAi,
                 boolean capeEnabled, boolean jacketEnabled, boolean leftSleeveEnabled, boolean rightSleeveEnabled, boolean leftPantsLegEnabled, boolean rightPantsLegEnabled, boolean hatEnabled,
                 float absorptionHearts, int score) {
        super(onFire, crouched, sprinting, eating, blocking, drinking, invisible, air, nameTag, alwaysShowNameTag, health, potionEffectColor, isPotionEffectAmbient, numberOfArrows, hasAi);
        this.capeEnabled = capeEnabled;
        this.jacketEnabled = jacketEnabled;
        this.leftSleeveEnabled = leftSleeveEnabled;
        this.rightSleeveEnabled = rightSleeveEnabled;
        this.leftPantsLegEnabled = leftPantsLegEnabled;
        this.rightPantsLegEnabled = rightPantsLegEnabled;
        this.hatEnabled = hatEnabled;
        this.absorptionHearts = absorptionHearts;
        this.score = score;
    }

    @Override
    public byte[] getFull() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        baos.writeBytes(super.getFull());
        byte skinFlags = 0;
        if (capeEnabled) skinFlags |= 0x01;
        if (jacketEnabled) skinFlags |= 0x02;
        if (leftSleeveEnabled) skinFlags |= 0x04;
        if (rightSleeveEnabled) skinFlags |= 0x08;
        if (leftPantsLegEnabled) skinFlags |= 0x10;
        if (rightPantsLegEnabled) skinFlags |= 0x20;
        if (hatEnabled) skinFlags |= 0x40;
        dos.writeByte(skinFlags);
        dos.writeByte(0);
        dos.writeFloat(absorptionHearts);
        dos.writeInt(score);

        return baos.toByteArray();
    }
}
