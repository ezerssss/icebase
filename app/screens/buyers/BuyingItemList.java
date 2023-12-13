package icebase.app.screens.buyers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Locale.Category;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.app.types.items.Item;
import icebase.icebase.Auth;
import icebase.icebase.User;

import java.util.Scanner;
import java.io.IOException;

public class BuyingItemList implements Screen {

    private String storeName;
    private String categoryName;
    private List<Item> itemList;
    private Scanner sc = App.sc;
    private Auth auth = Auth.getAuth();
    private User user = auth.getUser();

    public BuyingItemList(Store store, CATEGORY_ENUM category) {
        this.storeName = store.getName();
        this.categoryName = category.value;
        try {
            this.itemList = API.getStoreItems(store, category);
        } catch (IOException io) {
            io.printStackTrace();
        } catch (CategoryDoesNotExistException cdnee) {
            cdnee.printStackTrace();
        }

    }

    public void display() {
        while (true) {
            int choice;
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("[" + categoryName + "]");
            MenuTitle.displayOptions(itemList);

            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == itemList.size() + 1) {
                    break;
                }
                Item item = itemList.get(choice);
                this.buyItem(item);
            } catch (Exception e) {
                System.out.println("Please choose from the given options...\n");
            }
        }
    }

    public void buyItem(Item item) {
        int amount;
        String itemName = item.getName();
        while (true) {
            try {
                MenuTitle.displayStoreName(storeName);
                MenuTitle.displaySubTitle("[" + itemName + "]");
                System.out.println("Current money: " + user.getMoney() + "\n");
                item.displayDetails();
                System.out.println("Enter quantity: ");
                amount = sc.nextInt();
                if (amount <= 0) {
                    throw new Exception();
                }
                API.buy(item, amount);
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid amount...");
            }
        }
    }

}
