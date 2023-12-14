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
            System.out.print("Enter your store name: ");
            storeName = sc.nextLine();

            if (storeName.isEmpty()) {
                System.out.println(
                        MenuTitle.getErrorMessage("\nStore name cannot be empty. Please enter a valid store name.\n"));
            }
        }

        double startingMoney = 0;
        while (startingMoney <= 0) {
            try {
                System.out.print("\nEnter your starting money: ");
                String moneyInputStr = sc.nextLine();

                if (moneyInputStr.isEmpty()) {
                    System.out.println(
                            MenuTitle.getErrorMessage("Starting money cannot be empty. Please enter a valid amount."));
                    continue;
                }

                startingMoney = Double.parseDouble(moneyInputStr);

                if (startingMoney <= 0) {
                    System.out.println(MenuTitle
                            .getErrorMessage("Starting money cannot be 0 or negative. Please enter a valid amount."));
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(MenuTitle
                        .getErrorMessage("Invalid input. Please enter a valid numeric value for starting money."));
            }
        }

        try {
            API.setupUser(this.user, storeName, startingMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
