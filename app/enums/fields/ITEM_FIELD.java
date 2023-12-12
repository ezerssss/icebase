package icebase.app.enums.fields;

public enum ITEM_FIELD {
    ITEM_ID(0), STORE_ID(1), SELLER_ID(2), ITEM_NAME(3), PRICE(4), STOCK(5), CATEGORY(6);

    public final int index;

    private ITEM_FIELD(int index) {
        this.index = index;
    }
}
