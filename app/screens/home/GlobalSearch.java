package icebase.app.screens.home;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.app.types.items.Item;

public class GlobalSearch implements Screen {
    public void display() {
        do {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("GLOBAL ITEM SEARCH");

            String input = InputHelper.getNonEmptyString("Enter item name: ", "Item name cannot be empty.");

            System.out.printf("%n     %-20s%-15s%-10s%-20s%n", "Item Name", "Price", "Stock", "Store Name");

            try {
                List<Item> items = API.itemSearch(input);

                // Sort items by price
                Collections.sort(items, (prev, next) -> (int) (prev.getPrice() - next.getPrice()));

                if (items.isEmpty()) {
                    MenuTitle.displaySubTitle("NO ITEMS FOUND");
                }

                int count = 0;
                for (Item item : items) {
                    String name = item.getName();

                    String sellerId = item.getSellerId();
                    Store store = API.getUserStore(sellerId);
                    String storeName = store.getName();

                    Double price = item.getPrice();
                    int stock = item.getStock();

                    System.out.printf("%d.   %-20s%-15.2f%-10d%-20s%n", ++count, name, price, stock, storeName);
                }

                input = InputHelper.getNonEmptyString("\nSearch again? [Y/N] ", "");

                if (input.equalsIgnoreCase("n")) {
                    break;
                }
            } catch (CategoryDoesNotExistException | IOException e) {
                MenuTitle.printErrorMessage("Something went wrong with searching the database.");

                e.printStackTrace();
            } catch (Exception e) {
                MenuTitle.printErrorMessage("Unexpected error.");

                e.printStackTrace();
            }
        } while (true);
    }
}
