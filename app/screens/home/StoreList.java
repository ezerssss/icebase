package icebase.app.screens.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import icebase.app.App;
import icebase.app.api.API;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.icebase.Auth;
import icebase.icebase.User;

public class StoreList implements Screen {
    public void display() {
        Scanner sc = App.sc;
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
            System.out.println(MenuTitle.getErrorMessage(e.getMessage()));
            return;
        }

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("STORES");
            MenuTitle.displayOptions(storeNames);

            try {
                System.out.print("Choice: ");
                choice = Integer.parseInt(sc.nextLine());

                // Returns when the last option is picked
                if (choice == storeNames.size()) {
                    return;
                }

                chosenStore = stores.get(choice - 1);
                chosenStoreID = chosenStore.getSellerId();
                if (chosenStoreID.equals(currentUserID)) {
                    Router.navigate(SCREEN_ENUM.SELLING_VIEW);
                } else {
                    // Router.navigate(SCREEN_ENUM.BUYING_VIEW);

                    // For testing
                    System.out.println("BUYING VIEW");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(MenuTitle.getErrorMessage("Please choose from the given options...\n"));
            } catch (Exception e) {
                System.out.println(MenuTitle.getErrorMessage(e.getMessage()));

            }
        }

    }
}
