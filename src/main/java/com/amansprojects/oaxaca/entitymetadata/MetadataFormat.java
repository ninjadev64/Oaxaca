package com.amansprojects.oaxaca.entitymetadata;

public enum MetadataFormat {
    BYTE(0),
    SHORT(1),
    INT(2),
    FLOAT(3),
    STRING(4),
    SLOT(5),
    POSITION(6),
    DIRECTION(7);

    private final int type;
    MetadataFormat(int type) {
        this.type = type;
    }

    public byte getByte(int index) {
        return (byte) ((type << 5 | index & 0x1F) & 0xFF);
    }
}
