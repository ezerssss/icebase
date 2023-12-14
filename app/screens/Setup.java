package icebase.app.screens;

import java.io.IOException;
import java.util.Scanner;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.icebase.User;

public class Setup implements Screen {
    User user;

    public Setup(User user) {
        this.user = user;
    }

    public void display() {
        Scanner sc = App.sc;

        System.out.println();
        MenuTitle.displayMainTitle();
        MenuTitle.displaySubTitle("Set-up");

        String storeName = "";
        while (storeName.isEmpty()) {
            System.out.print("\nEnter your store name: ");
            storeName = sc.nextLine();

            if (storeName.isEmpty()) {
                MenuTitle.printErrorMessage("Store name cannot be empty. Please enter a valid store name.");
            }
        }

        double startingMoney = 0;
        while (startingMoney <= 0) {
            try {
                System.out.print("\nEnter your starting money: ");
                String moneyInputStr = sc.nextLine();

                if (moneyInputStr.isEmpty()) {
                    MenuTitle.printErrorMessage("Starting money cannot be empty. Please enter a valid amount.");
                    continue;
                }

                startingMoney = Double.parseDouble(moneyInputStr);

                if (startingMoney <= 0) {
                    MenuTitle.printErrorMessage("Starting money cannot be 0 or negative. Please enter a valid amount.");
                    continue;
                }
            } catch (NumberFormatException e) {
                MenuTitle.printErrorMessage("Invalid input. Please enter a valid numeric value for starting money.");
            }
        }

        try {
            API.setupUser(this.user, storeName, startingMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
