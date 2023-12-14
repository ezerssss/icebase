package icebase.app.screens.sellers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.app.types.items.Item;

import icebase.icebase.Auth;
import icebase.icebase.User;

public class SellingItemList implements Screen {

    private Auth auth = Auth.getAuth();
    private User user = auth.getUser();
    private String storeName;
    private List<Item> itemList;
    private ArrayList<String> itemNames;
    private Store store;

    public SellingItemList(Store store) {
        this.store = store;
        this.storeName = store.getName();
        try {
            this.itemList = API.getStoreItems(store);
            for (Item item : itemList) {
                itemNames.add(item.getName());
            }
            itemNames.add("Return");
        } catch (CategoryDoesNotExistException cdne) {
            System.out.println(cdne.getMessage());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void display() {
        Item item;
        int choice;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("Inventory");
            MenuTitle.displayOptions(itemNames);

            choice = InputHelper.getChoiceInt(itemList.size());
            if (choice == itemList.size()) {
                return;
            }
            item = itemList.get(choice - 1);

            try{
                manageItem(item);
            }catch(){

            }

        }

    }

    private void manageItem(Item item) {

        System.out.println("Item: " + item.getName());
        System.out.println("[1] Edit");
        System.out.println("[2] Delete");
        System.out.println("[3] Return");

        int choice = InputHelper.getChoiceInt(3);

        if (choice == 1) {
            editItemInventory(item);
        } else if (choice == 2) {
            deleteItem(item);
        }

    }

    private void editItemInventory(Item item) {
        int quantity = InputHelper.getPositiveInt("\nQuantity: ", "");
        double price = InputHelper.getPositiveDouble("Price: ", "");
    }

    private void deleteItem(Item item) {
        //
    }
}
