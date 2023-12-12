package icebase.app.screens.home;

import java.util.ArrayList;
import java.util.Scanner;
import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.screens.Screen;

public class StoreList implements Screen {
    public void display() {
        Scanner sc = App.sc;
        int choice;
        ArrayList<String> storeNames = new ArrayList<String>();

        ArrayList<String> stores = new ArrayList<String>(); // fecth from icebase (type should be Stores)
        stores.add("Store A"); // stand-in value
        stores.add("Store B");
        stores.add("Store C");

        for (String store : stores) { // String -> Store
            storeNames.add(store); // store -> store.getName()
        }
        storeNames.add("Return");

        int range = storeNames.size();

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("[STORES]");
            // MenuTitle.displayOptions(storeNames);

            try {
                System.out.print("Choice: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == range + 1) {
                    return;
                }
                System.out.println(storeNames.get(choice));
                // BuyingView(stores.get(choice));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
