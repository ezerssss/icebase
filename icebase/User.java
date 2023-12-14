package icebase.icebase;

import java.io.IOException;

import icebase.app.MenuTitle;
import icebase.icebase.enums.USER_FIELD;

public class User {
    private final String id;
    private final Doc userDoc;
    private final String username;
    private String password;
    private String storeId;
    private double money;

    public User(Doc userDoc) throws IOException {
        String[] data = userDoc.data().split(",");

        this.id = data[USER_FIELD.USER_ID.index];
        this.username = data[USER_FIELD.USERNAME.index];
        this.password = data[USER_FIELD.PASSWORD.index];
        this.userDoc = userDoc;

        if (data.length > 3) {
            this.storeId = data[USER_FIELD.STORE_ID.index];
            this.money = Double.parseDouble(data[USER_FIELD.MONEY.index]);
        }
    }

    public String getId() {
        return this.id;
    }

    public Doc getDoc() {
        return this.userDoc;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;

        this.updateDatabaseData();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;

        this.updateDatabaseData();
    }

    public double getMoney() {
        return this.money;
    }

    private void updateDatabaseData() {
        try {
            String updatedData = this.toCSVString();
            this.userDoc.setData(updatedData);
        } catch (IOException e) {
            MenuTitle.printErrorMessage(
                    "Something went wrong with updating user data to the database.\n" + e.getMessage());
        }
    }

    public void setMoney(double money) {
        this.money = money;

        this.updateDatabaseData();
    }

    public void increaseMoney(double amount) {
        amount = Math.max(0, amount);
        this.money += amount;

        this.updateDatabaseData();
    }

    public void decreaseMoney(double amount) {
        amount = Math.max(0, amount);
        this.money -= amount;

        this.updateDatabaseData();
    }

    public String toCSVString() {
        String[] data = { this.id, this.username, this.password, this.storeId, Double.toString(this.money) };
        return String.join(",", data);
    }
}
