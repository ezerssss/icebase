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

public class SellingItemList implements Screen {

    private String storeName;
    private List<Item> itemList;
    private ArrayList<String> itemNames;

    public SellingItemList(Store store) {
        this.storeName = store.getName();
        try {
            this.itemList = API.getStoreItems(store);
            for (Item item : itemList) {
                itemNames.add(item.getName());
            }
            itemNames.add("Return");
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
            MenuTitle.displaySubTitle("Inventory");
            MenuTitle.displayOptions(itemNames);

            choice = InputHelper.getChoiceInt(itemList.size());
            if (choice == itemList.size()) {
                return;
            }
            item = itemList.get(choice - 1);

            try {
                editItemInventory(item);
            } catch (IOException io) {
                io.printStackTrace();
            }

        }

    }

    private void editItemInventory(Item item) throws IOException {
        System.out.println("Item: " + item.getName());
        int quantity = InputHelper.getPositiveInt("\nQuantity: ", "");
        double price = InputHelper.getPositiveDouble("Price: ", "");
        item.setStock(quantity);
        item.setPrice(price);
        System.out.println("Item successfully updated!!!\n");
    }
}
