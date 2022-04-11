package com.amansprojects.oaxaca.entitymetadata;

public class Player extends Living {
    public float additionalHearts = 0;
    public int score = 0;

    public boolean capeEnabled = false;
    public boolean jacketEnabled = false;
    public boolean leftSleeveEnabled = false;
    public boolean rightSleeveEnabled = false;
    public boolean leftPantsLegEnabled = false;
    public boolean rightPantsLegEnabled = false;
    public boolean hatEnabled = false;

    public byte mainHand = 1;

    public Player(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  boolean isHandActive, boolean activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra, isHandActive, activeHand, health, potionEffectColor, isPotionEffectAmbient, numberOfArrows);
    }

    public Player(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  int air, String customName, boolean isCustomNameVisible, boolean isSilent, boolean noGravity,
                  boolean isHandActive, boolean activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra, air, customName, isCustomNameVisible, isSilent, noGravity, isHandActive, activeHand, health, potionEffectColor, isPotionEffectAmbient, numberOfArrows);
    }


    public Player(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  boolean isHandActive, boolean activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows,
                  float additionalHearts, int score, boolean capeEnabled, boolean jacketEnabled, boolean leftSleeveEnabled, boolean rightSleeveEnabled, boolean leftPantsLegEnabled, boolean rightPantsLegEnabled, boolean hatEnabled, byte mainHand) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra, isHandActive, activeHand, health, potionEffectColor, isPotionEffectAmbient, numberOfArrows);
        this.additionalHearts = additionalHearts;
        this.score = score;
        this.capeEnabled = capeEnabled;
        this.jacketEnabled = jacketEnabled;
        this.leftSleeveEnabled = leftSleeveEnabled;
        this.rightSleeveEnabled = rightSleeveEnabled;
        this.leftPantsLegEnabled = leftPantsLegEnabled;
        this.rightPantsLegEnabled = rightPantsLegEnabled;
    }

    public Player(boolean onFire, boolean crouching, boolean sprinting, boolean invisible, boolean glowingEffect, boolean flyingWithElytra,
                  int air, String customName, boolean isCustomNameVisible, boolean isSilent, boolean noGravity,
                  boolean isHandActive, boolean activeHand, float health, int potionEffectColor, boolean isPotionEffectAmbient, int numberOfArrows,
                  float additionalHearts, int score, boolean capeEnabled, boolean jacketEnabled, boolean leftSleeveEnabled, boolean rightSleeveEnabled, boolean leftPantsLegEnabled, boolean rightPantsLegEnabled, boolean hatEnabled, byte mainHand) {
        super(onFire, crouching, sprinting, invisible, glowingEffect, flyingWithElytra, air, customName, isCustomNameVisible, isSilent, noGravity, isHandActive, activeHand, health, potionEffectColor, isPotionEffectAmbient, numberOfArrows);
        this.additionalHearts = additionalHearts;
        this.score = score;
        this.capeEnabled = capeEnabled;
        this.jacketEnabled = jacketEnabled;
        this.leftSleeveEnabled = leftSleeveEnabled;
        this.rightSleeveEnabled = rightSleeveEnabled;
        this.leftPantsLegEnabled = leftPantsLegEnabled;
        this.rightPantsLegEnabled = rightPantsLegEnabled;
    }
}
