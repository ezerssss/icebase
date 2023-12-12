package icebase.app.types.items;

import java.io.IOException;

import icebase.icebase.Doc;

enum ITEM_FIELD {
    ITEM_ID(0), STORE_ID(1), SELLER_ID(2), ITEM_NAME(3), PRICE(4), STOCK(5), CATEGORY(6), SHELF_LIFE(7);

    public final int index;

    private ITEM_FIELD(int index) {
        this.index = index;
    }
}

public class Food extends Item {
    private int shelfLife; // NOTE: Temporary new attribute for Food, we can change this

    public Food(Doc doc) throws IOException {
        this.doc = doc;

        String[] data = doc.data().split(",");

        this.id = data[ITEM_FIELD.ITEM_ID.index];
        this.storeId = data[ITEM_FIELD.STORE_ID.index];
        this.sellerId = data[ITEM_FIELD.SELLER_ID.index];
        this.name = data[ITEM_FIELD.ITEM_ID.index];
        this.price = Double.parseDouble(data[ITEM_FIELD.PRICE.index]);
        this.stock = Integer.parseInt(data[ITEM_FIELD.STOCK.index]);
        this.category = data[ITEM_FIELD.CATEGORY.index];
        this.shelfLife = Integer.parseInt(data[ITEM_FIELD.SHELF_LIFE.index]);
    }

    // NOTE: Change this to be complete, basically guide lang ni for the other types
    // of Item
    public void displayDetails() {
        System.out.println(shelfLife);
    }

    public String toCSVString() {
        String[] data = { this.getId(), this.getStoreId(), this.getSellerId(), this.getName(),
                Double.toString(this.getPrice()), Integer.toString(this.getStock()), this.getCategory(),
                Integer.toString(this.shelfLife) };

        return String.join(",", data);
    }
}
