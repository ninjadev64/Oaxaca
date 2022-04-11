package com.amansprojects.oaxaca.entitymetadata;

public class Living extends Entity {
    public boolean isHandActive;
    public boolean activeHand = false; // false for main hand, true for offhand
    public float health;
    public int potionEffectColor;
    public boolean isPotionEffectAmbient;
    public int numberOfArrows;

    public Living(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  boolean isHandActive, boolean activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra);
        this.isHandActive = isHandActive;
        this.activeHand = activeHand;
        this.health = health;
        this.potionEffectColor = potionEffectColor;
        this.isPotionEffectAmbient = isPotionEffectAmbient;
        this.numberOfArrows = numberOfArrows;
    }

    public Living(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  int air, String customName, boolean isCustomNameVisible, boolean isSilent, boolean noGravity,
                  boolean isHandActive, boolean activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra, air, customName, isCustomNameVisible, isSilent, noGravity);
        this.isHandActive = isHandActive;
        this.activeHand = activeHand;
        this.health = health;
        this.potionEffectColor = potionEffectColor;
        this.isPotionEffectAmbient = isPotionEffectAmbient;
        this.numberOfArrows = numberOfArrows;
    }

    public byte getHandBitMask() {
        byte value = 0;
        if (isHandActive) value = (byte)(value | 0x01);
        if (activeHand) value = (byte)(value | 0x02);
        return value;
    }
}
