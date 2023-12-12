package icebase.app.enums;

public enum COLLECTION_ENUM {
    STORES("stores"), ITEMS("items"), USERS("users");

    public final String value;

    private COLLECTION_ENUM(String value) {
        this.value = value;
    }
}
