package icebase.app.screens.buyers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.exceptions.BuyException;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.exceptions.UnauthorizedException;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.app.types.items.Item;
import icebase.icebase.Auth;
import icebase.icebase.User;

public class BuyingItemList implements Screen {

    private String storeName;
    private String categoryName;
    private List<Item> itemList;
    private ArrayList<String> itemNames;
    private Scanner sc = App.sc;
    private Auth auth = Auth.getAuth();
    private User user = auth.getUser();

    public BuyingItemList(Store store, CATEGORY_ENUM category) {
        this.storeName = store.getName();
        this.categoryName = category.value;
        try {
            this.itemList = API.getStoreItems(store, category);
            for (Item item : itemList) {
                itemNames.add(item.getName());
            }
            itemNames.add("return");

        } catch (CategoryDoesNotExistException cdne) {
            System.out.println(cdne.getMessage());
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public void display() {
        while (true) {
            int choice;
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle(categoryName);
            MenuTitle.displayOptions(itemNames);

            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == itemList.size()) {
                    return;
                }
                Item item = itemList.get(choice - 1);
                this.buyItem(item);
            } catch (UnauthorizedException u) {
                System.out.println(u.getMessage());
            } catch (CategoryDoesNotExistException cdne) {
                System.out.println(cdne.getMessage());
            } catch (BuyException b) {
                System.out.println(b.getMessage());
            } catch (IOException io) {
                io.printStackTrace();
            } catch (Exception e) {
                System.out.println("Please choose from the given options...\n");
            }
        }
    }

    public void buyItem(Item item)
            throws UnauthorizedException, CategoryDoesNotExistException, BuyException, IOException {
        int amount;
        String itemName = item.getName();
        MenuTitle.displayStoreName(storeName);
        MenuTitle.displaySubTitle(itemName);
        item.displayDetails();
        System.out.println("Current money: " + user.getMoney());
        while (true) {
            try {
                System.out.println("Enter quantity: ");
                amount = Integer.parseInt(sc.nextLine());
                if (amount > 0) {
                    break;
                }
                System.out.println("Quantity must be greater than 0. Please try again...\n");
            } catch (NumberFormatException nf) {
                System.out.println("Cannot quantity the value. Please try again...\n");
            }
        }
        API.buy(item, amount); // error thrown are catch by the display method so user can choose another item
    }
}
