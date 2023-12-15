package icebase.app.enums;

public enum CATEGORY_ENUM {
    FOOD("Food"), HOUSEHOLD_ITEMS("Household Items"), ELECTRONICS("Electronics"), CLOTHING("Clothing"),
    HARDWARE("Hardware");

    public final String value;

    private CATEGORY_ENUM(String value) {
        this.value = value;
    }
}
