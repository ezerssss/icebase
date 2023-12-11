package icebase.app.screens.buyers;

import icebase.app.App;

import java.util.Scanner;

import icebase.app.MenuTitle;
import icebase.app.screens.Screen;

public class BuyingView implements Screen {

    public void display() {
        Scanner sc = App.sc;
        int choice;
        String storeName = "STORE X"; // stand-in value, should be set from constructor
        String[] categoryList = { "Category A", "Category B", "Category C", "Return" }; // stand-in list
        int range = categoryList.length;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("[CATEGORIES]");
            MenuTitle.displayOptions(categoryList);

            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == range + 1) {
                    break;
                }
                String category = categoryList[choice - 1]; // fetch from icebase
                System.out.println(category);// go to item list
            } catch (Exception e) {
                System.out.println("Please choose from the given options...\n");
            }
        }

    }
}
