package icebase.app;

import java.util.Scanner;

public class CategoryView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String storeName = "STORE X"; // stand-in value, should be set from constructor
        String[] categoryList = { "Category A", "Category B", "Category C" }; // stand-in list
        int range = categoryList.length;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("[CATEGORIES]");
            for (int i = 0; i < range; i++) {
                System.out.printf(
                        "\t\t[%d] - %s%n", i + 1, categoryList[i]);
            }
            System.out.printf("\t\t[%d] - Return%n", range + 1);

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

        sc.close();
    }
}
