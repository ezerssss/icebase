package icebase.app;

import java.util.Scanner;

public class ItemList {
    ItemList() {

    }

    public static void buyItem(String item) {
        System.out.println(item);
        // display other item attributes
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String storeName = "STORE X"; // stand-in value, should be set from constructor
        String categoryName = "CATEGORY X"; // stand-in value, should be set from constructor
        String[] itemList = { "Item A", "Item B", "Item C" }; // fetch from icebase
        int range = itemList.length;
        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("[" + categoryName + "]");
            for (int i = 0; i < range; i++) {
                System.out.printf(
                        "\t\t[%d] - %s%n", i + 1, itemList[i]);
            }
            System.out.printf("\t\t[%d] - Return%n", range + 1);

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

        sc.close();
    }
}
