package com.amansprojects.oaxaca;

public enum Gamemode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3),

    SURVIVAL_HARDCORE(0, true),
    CREATIVE_HARDCORE(1, true),
    ADVENTURE_HARDCORE(2, true),
    SPECTATOR_HARDCORE(3, true);

    public final byte modeByte;
    public final boolean hardcore;

    Gamemode(byte modeByte) { this.modeByte = modeByte; hardcore = false; }
    Gamemode(int modeByte) { this.modeByte = (byte) modeByte; hardcore = false; }

    Gamemode(byte modeByte, boolean hardcore) { this.modeByte = modeByte; this.hardcore = hardcore; }
    Gamemode(int modeByte, boolean hardcore) { this.modeByte = (byte) modeByte; this.hardcore = hardcore; }

    public byte get() {
        if (hardcore) return (byte) (modeByte | 0x8);
        else return modeByte;
    }
}
