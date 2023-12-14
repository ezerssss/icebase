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

            item = itemList.get(choice - 1);

            try {
                System.out.println("Item: " + item.getName());
                System.out.println("[1] Edit");
                System.out.println("[2] Delete");
                System.out.println("[3] Return");

                choice = InputHelper.getChoiceInt(3);

                if (choice == 1) {
                    editItemInventory(item);
                } else if (choice == 2) {
                    deleteItem(item);
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
        System.out.println("Item: " + item.getName());
        int quantity = InputHelper.getPositiveInt("\nQuantity: ", "");
        double price = InputHelper.getPositiveDouble("Price: ", "");
        item.setStock(quantity);
        item.setPrice(price);
        System.out.println("Item successfully updated!!!\n");
    }

    private void deleteItem(Item item) throws IOException, DocumentDoesNotExist {
        item.getDoc().delete();
        System.out.println("Item successfully updated!!!\n");
    }
}
