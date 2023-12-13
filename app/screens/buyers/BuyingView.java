package icebase.app.screens.buyers;

import icebase.app.App;

import java.util.ArrayList;
import java.util.Scanner;

import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.screens.Screen;
import icebase.app.types.Store;

public class BuyingView implements Screen {
    private String storeName;
    private Store store;
    private Scanner sc = App.sc;
    private ArrayList<String> categoryNames = new ArrayList<>();
    private int choice;

    public BuyingView(Store store) {
        this.store = store;
        this.storeName = store.getName();
        for (CATEGORY_ENUM categoryENUM : CATEGORY_ENUM.values()) {
            categoryNames.add(categoryENUM.value);
        }
        categoryNames.add("return");
    }

    public void display() {
        CATEGORY_ENUM category;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("CATEGORIES");
            MenuTitle.displayOptions(categoryNames);
            getChoice(); // gets and validates the choice given by the user
            if (choice == categoryNames.size()) {
                return;
            }
            category = CATEGORY_ENUM.values()[choice - 1];
            Router.nagivate(SCREEN_ENUM.BUYING_ITEM_LIST, store, category);
        }
    }

    private void getChoice() {
        while (true) {
            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 0 && choice <= categoryNames.size()) { // meaning within the options
                    return;
                }
                System.out.println("Please choose from the given options...\n");
            } catch (NumberFormatException nf) {
                System.out.println("Please choose from the given options...\n");
            }
        }
    }
}
