package icebase.app.types.items;

import java.io.IOException;
import java.time.LocalDate;

import icebase.icebase.Doc;
import icebase.app.MenuTitle;
import icebase.app.enums.fields.ITEM_FIELD;

public class Electronics extends Item {
    private int warrantyPeriod;
    private LocalDate currenDate;

    private static final int WARRANTY_PERIOD_INDEX = 7;

    public Electronics(Doc doc) throws IOException {
        this.doc = doc;

        String[] data = doc.data().split(",");

        this.id = data[ITEM_FIELD.ITEM_ID.index];
        this.storeId = data[ITEM_FIELD.STORE_ID.index];
        this.sellerId = data[ITEM_FIELD.SELLER_ID.index];
        this.name = data[ITEM_FIELD.ITEM_NAME.index];
        this.price = Double.parseDouble(data[ITEM_FIELD.PRICE.index]);
        this.stock = Integer.parseInt(data[ITEM_FIELD.STOCK.index]);
        this.category = data[ITEM_FIELD.CATEGORY.index];
        this.warrantyPeriod = Integer.parseInt(data[WARRANTY_PERIOD_INDEX]);
        currenDate = LocalDate.now();
    }

    public void displayDetails() {
        System.out.println(border);
        System.out.println("Price: " + price + "\nStock: " + stock);
        LocalDate expirationDate = currenDate.plusMonths(warrantyPeriod);
        System.out.println("Warranty Period: " + warrantyPeriod + "\nCovered until: " + expirationDate);
        System.out.println(border + "\n");

    }

    public String toCSVString() {
        String[] data = { this.getId(), this.getStoreId(), this.getSellerId(), this.getName(),
                Double.toString(this.getPrice()), Integer.toString(this.getStock()), this.getCategory(),
                Integer.toString(this.warrantyPeriod) };

        return String.join(",", data);
    }
}
