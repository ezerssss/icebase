package icebase.app.types.items;

import java.io.IOException;
import java.time.LocalDate;

import icebase.app.MenuTitle;
import icebase.app.enums.fields.ITEM_FIELD;
import icebase.icebase.Doc;

public class Clothing extends Item {
    private String size;
    private String material;
    private String color;

    private static final int SIZE_INDEX = 7;
    private static final int MATERIAL_INDEX = 8;
    private static final int COLOR_INDEX = 9;

    public Clothing(Doc doc) throws IOException {
        this.doc = doc;

        String[] data = doc.data().split(",");

        this.id = data[ITEM_FIELD.ITEM_ID.index];
        this.storeId = data[ITEM_FIELD.STORE_ID.index];
        this.sellerId = data[ITEM_FIELD.SELLER_ID.index];
        this.name = data[ITEM_FIELD.ITEM_NAME.index];
        this.price = Double.parseDouble(data[ITEM_FIELD.PRICE.index]);
        this.stock = Integer.parseInt(data[ITEM_FIELD.STOCK.index]);
        this.category = data[ITEM_FIELD.CATEGORY.index];
        this.size = data[SIZE_INDEX];
        this.material = data[MATERIAL_INDEX];
        this.color = data[COLOR_INDEX];
    }

    public void displayDetails() {
        MenuTitle.displaySubBorder();
        System.out.printf("Price: %.2f%nStock: %d%n", price, stock);
        System.out.println("Size: " + size);
        System.out.println("Material: " + material);
        System.out.println("Color: " + color);
        System.out.println(border + "\n");

    }

    public String toCSVString() {
        String[] data = { this.getId(), this.getStoreId(), this.getSellerId(), this.getName(),
                Double.toString(this.getPrice()), Integer.toString(this.getStock()), this.getCategory(),
                this.size, this.material, this.color };

        return String.join(",", data);
    }
}
