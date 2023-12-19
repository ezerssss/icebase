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
import icebase.icebase.exceptions.DocumentDoesNotExist;

public class SellingItemList implements Screen {

    private String storeName;
    private List<Item> itemList;
    private ArrayList<String> itemNames = new ArrayList<>();

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
        int itemIndex;
        ArrayList<String> options = new ArrayList<>();
        options.add("Edit");
        options.add("Delete");
        options.add("Return");

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("Inventory");

            if (itemList.isEmpty()) {
                MenuTitle.displaySubTitle("No Items");
            }

            MenuTitle.displayOptions(itemNames);

            choice = InputHelper.getChoiceInt(itemNames.size());
            if (choice == itemNames.size()) {
                return;
            }

            itemIndex = choice - 1;

            item = itemList.get(itemIndex);

            try {
                MenuTitle.displayMainBorder();
                MenuTitle.displaySubTitle(item.getName());
                item.displayDetails();
                MenuTitle.displayOptions(options);

                choice = InputHelper.getChoiceInt(3);

                if (choice == 1) {
                    editItemInventory(item);
                } else if (choice == 2) {
                    deleteItem(item, itemIndex);
                } else if (choice == 3) {
                    break;
                }
            } catch (DocumentDoesNotExist e) {
                MenuTitle.printErrorMessage(e.getMessage());
            } catch (IOException io) {
                io.printStackTrace();
            } catch (Exception e) {
                MenuTitle.printErrorMessage("An unexpected error occured.");

                e.printStackTrace();
            }

        }

    }

    private void editItemInventory(Item item) throws IOException {
        MenuTitle.displaySubBorder();
        int quantity = InputHelper.getPositiveInt("Quantity: ",
                "Quantity must be greater than 0. Please try again...\n");
        double price = InputHelper.getPositiveDouble("Price: ", "Price must be greater than 0. Please try again...\\n" + //
                "");
        MenuTitle.displaySubBorder();

        item.setStock(quantity);
        item.setPrice(price);
        MenuTitle.printSuccessMessage("Item successfully updated!");
    }

    private void deleteItem(Item item, int itemIndex) throws IOException, DocumentDoesNotExist {
        itemList.remove(itemIndex);
        itemNames.remove(itemIndex);

        item.getDoc().delete();
        MenuTitle.printSuccessMessage("Item successfully deleted!");
    }
}
