package icebase.app.screens.buyers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.exceptions.BuyException;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.exceptions.UnauthorizedException;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.app.types.items.Item;
import icebase.icebase.Auth;
import icebase.icebase.User;

public class BuyingItemList implements Screen {

    private String storeName;
    private String categoryName;
    private List<Item> itemList;
    private ArrayList<String> itemNames = new ArrayList<>();
    private Auth auth = Auth.getAuth();
    private User user = auth.getUser();

    private void generateChoices() {
        Set<String> uniqueItemNames = new HashSet<>();
        String name;
        String itemIdentifier;
        boolean hasDuplicate;
        for (Item item : itemList) {
            name = item.getName();
            hasDuplicate = !(uniqueItemNames.add(name));
            if (hasDuplicate) {
                itemIdentifier = " - " + item.getId().substring(0, 3);
                itemNames.add(name + itemIdentifier);
            } else {
                itemNames.add(name);
            }
        }

        itemNames.add("Return");
    }

    public BuyingItemList(Store store) {
        this.storeName = store.getName();
        this.categoryName = "All";
        try {
            this.itemList = API.getStoreItems(store);
            this.generateChoices();
        } catch (CategoryDoesNotExistException cdne) {
            MenuTitle.printErrorMessage(cdne.getMessage());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public BuyingItemList(Store store, CATEGORY_ENUM category) {
        this.storeName = store.getName();
        this.categoryName = category.value;
        try {
            this.itemList = API.getStoreItems(store, category);
            this.generateChoices();
        } catch (CategoryDoesNotExistException cdne) {
            MenuTitle.printErrorMessage(cdne.getMessage());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void display() {
        Item item;
        int choice;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle(categoryName);

            if (itemList.isEmpty()) {
                MenuTitle.displaySubTitle("No Items");
            }

            MenuTitle.displayOptions(itemNames);

            choice = InputHelper.getChoiceInt(itemNames.size());
            if (choice == itemNames.size()) {
                return;
            }

            item = itemList.get(choice - 1);

            try {
                buyItem(item);
            } catch (UnauthorizedException | CategoryDoesNotExistException | BuyException e) {
                MenuTitle.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void buyItem(Item item)
            throws UnauthorizedException, CategoryDoesNotExistException, BuyException, IOException {
        String itemName = item.getName();
        MenuTitle.displayStoreName(storeName);
        MenuTitle.displaySubTitle(itemName);
        item.displayDetails();
        System.out.printf("Current money: %.2f%n", user.getMoney());

        int amount = InputHelper.getPositiveInt("Enter quantity: ",
                "Quantity must be greater than 0. Please try again...\n");
        API.buy(item, amount); // error thrown are catch by the display method so user can choose another item
        MenuTitle.printSuccessMessage("Item successfully purchased!");
    }
}
