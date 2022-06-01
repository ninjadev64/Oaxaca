package com.amansprojects.oaxaca.entitymetadata;

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
}
