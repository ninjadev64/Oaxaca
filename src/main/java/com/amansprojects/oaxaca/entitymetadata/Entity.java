package com.amansprojects.oaxaca.entitymetadata;

public class Entity {
    public boolean onFire;
    public boolean crouching;
    public boolean sprinting;
    public boolean invisible;
    public boolean glowingEffect;
    public boolean flyingWithElytra;

    public int air = 300;
    public String customName = "";
    public boolean isCustomNameVisible = false;
    public boolean isSilent = false;
    public boolean noGravity = false;

    public Entity(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra) {
        this.onFire = onFire;
        this.crouching = crouching;
        this.sprinting = sprinting;
        this.invisible = invisible;
        this.glowingEffect = glowingEffect;
        this.flyingWithElytra = flyingWithElytra;
    }

    public Entity(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  int air, String customName, boolean isCustomNameVisible, boolean isSilent, boolean noGravity) {
        this.onFire = onFire;
        this.crouching = crouching;
        this.sprinting = sprinting;
        this.invisible = invisible;
        this.glowingEffect = glowingEffect;
        this.flyingWithElytra = flyingWithElytra;

        this.air = air;
        this.customName = customName;
        this.isCustomNameVisible = isCustomNameVisible;
        this.isSilent = isSilent;
        this.noGravity = noGravity;
    }

    public byte getBitMask() {
        byte value = 0;
        if (onFire) value = (byte) (value | 0x01);
        if (crouching) value = (byte) (value | 0x02);
        if (sprinting) value = (byte) (value | 0x08);
        if (invisible) value = (byte) (value | 0x20);
        if (glowingEffect) value = (byte) (value | 0x40);
        if (flyingWithElytra) value = (byte) (value | 0x80);
        return value;
    }
}
