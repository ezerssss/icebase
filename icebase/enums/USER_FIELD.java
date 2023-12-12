package icebase.icebase.enums;

public enum USER_FIELD {
    USER_ID(0), USERNAME(1), PASSWORD(2), STORE_ID(3), MONEY(4);

    public final int index;

    USER_FIELD(int fieldIndex) {
        this.index = fieldIndex;
    }
}
