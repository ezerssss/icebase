package icebase.app.types.items;

import java.io.IOException;
import java.time.LocalDate;

import icebase.app.MenuTitle;
import icebase.app.enums.fields.ITEM_FIELD;
import icebase.icebase.Doc;

public class Hardware extends Item {
    private String type;

    private static final int TYPE_INDEX = 7;

    public Hardware(Doc doc) throws IOException {
        this.doc = doc;

        String[] data = doc.data().split(",");

        this.id = data[ITEM_FIELD.ITEM_ID.index];
        this.storeId = data[ITEM_FIELD.STORE_ID.index];
        this.sellerId = data[ITEM_FIELD.SELLER_ID.index];
        this.name = data[ITEM_FIELD.ITEM_NAME.index];
        this.price = Double.parseDouble(data[ITEM_FIELD.PRICE.index]);
        this.stock = Integer.parseInt(data[ITEM_FIELD.STOCK.index]);
        this.category = data[ITEM_FIELD.CATEGORY.index];
        this.type = data[TYPE_INDEX];
    }

    public void displayDetails() {
        MenuTitle.displaySubBorder();
        System.out.println("Price: " + price + "\nStock: " + stock);
        System.out.println("Type: " + type);
        System.out.println(border + "\n");

    }

    public String toCSVString() {
        String[] data = { this.getId(), this.getStoreId(), this.getSellerId(), this.getName(),
                Double.toString(this.getPrice()), Integer.toString(this.getStock()), this.getCategory(),
                this.type };

        return String.join(",", data);
    }
}
