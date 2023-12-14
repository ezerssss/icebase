package icebase.app.screens.home;

import java.util.ArrayList;
import java.util.List;

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
    public void display() {
        int choice;

        Store chosenStore;
        String chosenStoreID;
        User currentUser = Auth.getAuth().getUser();
        String currentUserID = currentUser.getId();

        List<Store> stores = new ArrayList<>();
        ArrayList<String> storeNames = new ArrayList<>();

        // Gets names of each stores for options list
        try {
            stores = API.getStores();

            for (Store store : stores) {
                storeNames.add(store.getName());
            }
            storeNames.add("Return");

        } catch (Exception e) {
            MenuTitle.printErrorMessage(e.getMessage());
            return;
        }

        int range = storeNames.size();

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
