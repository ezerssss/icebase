package icebase.app.screens.home;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import icebase.app.api.API;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.icebase.Auth;
import icebase.icebase.User;

public class StoreList implements Screen {
    private List<Store> stores = new ArrayList<>();
    private ArrayList<String> storeNames = new ArrayList<>();

    public StoreList() {
        // Gets names of each store for options list
        Set<String> uniqueStoreNames = new HashSet<>();
        String name;
        String storeIdentifier;
        boolean hasDuplicate;
        try {
            stores = API.getStores();
            for (Store store : stores) {
                name = store.getName();
                hasDuplicate = !(uniqueStoreNames.add(name));
                if (hasDuplicate) {
                    storeIdentifier = " - " + store.getSellerId().substring(0, 3);
                    storeNames.add(name + storeIdentifier);
                } else {
                    storeNames.add(name);
                }
            }
            storeNames.add("Return");
        } catch (Exception e) {
            MenuTitle.printErrorMessage(e.getMessage());
        }
    }

    public void display() {
        int choice;
        int range = storeNames.size();
        Store chosenStore;
        String chosenStoreID;
        User currentUser = Auth.getAuth().getUser();
        String currentUserID = currentUser.getId();

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("STORES");
            MenuTitle.displayOptions(storeNames);

            try {
                choice = InputHelper.getChoiceInt(range);
                if (choice == range) {
                    return;
                }

                chosenStore = stores.get(choice - 1);
                chosenStoreID = chosenStore.getSellerId();
                if (chosenStoreID.equals(currentUserID)) {
                    Router.navigate(SCREEN_ENUM.SELLING_VIEW, chosenStore);
                } else {
                    Router.navigate(SCREEN_ENUM.BUYING_VIEW, chosenStore);
                }
            } catch (Exception e) {
                MenuTitle.printErrorMessage(e.getMessage());

            }
        }

    }
}
