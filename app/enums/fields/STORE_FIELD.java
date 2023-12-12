package icebase.app.enums.fields;

public enum STORE_FIELD {
    STORE_ID(0), SELLER_ID(1), STORE_NAME(2);

    public final int index;

    private STORE_FIELD(int index) {
        this.index = index;
    }
}
