package com.amansprojects.oaxaca.entitymetadata;

public class Entity {
    public boolean onFire;
    public boolean crouched;
    public boolean sprinting;
    public boolean eating; public boolean blocking; public boolean drinking;
    public boolean invisible;

    public short air;

    public Entity(boolean onFire, boolean crouched, boolean sprinting, boolean eating, boolean blocking, boolean drinking, boolean invisible, short air) {
        this.onFire = onFire;
        this.crouched = crouched;
        this.sprinting = sprinting;
        this.eating = eating;
        this.blocking = blocking;
        this.drinking = drinking;
        this.invisible = invisible;
        this.air = air;
    }
}
