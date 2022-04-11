package com.amansprojects.oaxaca.entitymetadata;

public class Living extends Entity {
    public boolean isHandActive;
    public int activeHand = 0;
    public float health;
    public int potionEffectColor;
    public boolean isPotionEffectAmbient;
    public int numberOfArrows;

    public Living(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  boolean isHandActive, int activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows) {
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
                  boolean isHandActive, int activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra, air, customName, isCustomNameVisible, isSilent, noGravity);
        this.isHandActive = isHandActive;
        this.activeHand = activeHand;
        this.health = health;
        this.potionEffectColor = potionEffectColor;
        this.isPotionEffectAmbient = isPotionEffectAmbient;
        this.numberOfArrows = numberOfArrows;
    }
}
