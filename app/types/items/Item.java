package icebase.app.types.items;

import java.io.IOException;

import icebase.icebase.Doc;

public abstract class Item {
    protected String id;
    protected String storeId;
    protected String sellerId;
    protected String name;
    protected double price;
    protected int stock;
    protected String category;
    protected Doc doc;

    public abstract void displayDetails();

    public abstract String toCSVString();

    public Doc getDoc() {
        return this.doc;
    }

    private void updateDocumentData() throws IOException {
        if (this.doc == null) {
            return;
        }

        String updatedData = this.toCSVString();
        this.doc.setData(updatedData);
    }

    public String getId() {
        return this.id;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public String getSellerId() {
        return this.sellerId;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public void increaseStock(int value) throws IOException {
        value = Math.max(0, value);

        this.stock += value;
        this.updateDocumentData();
    }

    public void decreaseStock(int value) throws IOException {
        value = Math.max(0, value);

        this.stock -= value;
        this.updateDocumentData();
    }

    public String getCategory() {
        return this.category;
    }
}
