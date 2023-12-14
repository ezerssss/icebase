package icebase.app.screens;

import java.io.IOException;

import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.helpers.InputHelper;
import icebase.icebase.User;

public class Setup implements Screen {
    User user;

    public Setup(User user) {
        this.user = user;
    }

    public void display() {
        System.out.println();
        MenuTitle.displayMainTitle();
        MenuTitle.displaySubTitle("Set-up");

        String storeName = InputHelper.getNonEmptyString("Enter your store name: ",
                "\nStore name cannot be empty. Please enter a valid store name.\n");

        double startingMoney = InputHelper.getPositiveDouble("\nEnter your starting money: ",
                "Starting money cannot be 0 or negative. Please enter a valid amount.");

        try {
            API.setupUser(this.user, storeName, startingMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
