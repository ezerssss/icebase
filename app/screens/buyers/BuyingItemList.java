package icebase.app.screens.buyers;

import java.util.Scanner;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.screens.Screen;
import java.util.Scanner;

public class BuyingItemList implements Screen {

    public void display() {
        Scanner sc = App.sc;
        int choice;
        String storeName = "STORE X"; // stand-in value, should be set from constructor
        String categoryName = "CATEGORY X"; // stand-in value, should be set from constructor
        String[] itemList = { "Item A", "Item B", "Item C", "Return" }; // fetch from icebase
        int range = itemList.length;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("[" + categoryName + "]");
            MenuTitle.displayOptions(itemList);

            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == range + 1) {
                    break;
                }
                String item = itemList[choice - 1]; // fetch from icebase
                buyItem(item);
            } catch (Exception e) {
                System.out.println("Please choose from the given options...\n");
            }
        }
    }

    public static void buyItem(String item) {
        System.out.println(item);
        // display other item attributes
    }

}
