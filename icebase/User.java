package icebase.icebase;

import java.io.IOException;

public class User {
    private final String id;
    private final Doc userDoc;
    private final String username;
    private String password;
    private String storeId;
    private double money;

    public User(String id, String username, String password, Doc userDoc) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userDoc = userDoc;
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
            String updatedData = String.join(",", this.getData());
            this.userDoc.setData(updatedData);
        } catch (IOException e) {
            System.out.println("Something went wrong with updating user data to the database.\n" + e.getMessage());
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

    public String[] getData() {
        return new String[] { this.id, this.username, this.password, this.storeId, Double.toString(this.money) };
    }
}
