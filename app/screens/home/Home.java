package icebase.app.screens.home;

import java.util.ArrayList;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;
import icebase.icebase.Auth;

public class Home implements Screen {
    private static final Auth auth = Auth.getAuth();

    public void display() {
        int choice;
        ArrayList<String> options = new ArrayList<>();
        options.add("Visit Stores");
        options.add("Item Search");
        options.add("Log-out");

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);

            choice = InputHelper.getChoiceInt(options.size());
            if (choice == 1) {
                Router.navigate(SCREEN_ENUM.STORE_LIST);
            } else if (choice == 2) {
                Router.navigate(SCREEN_ENUM.GLOBAL_SEARCH);
            } else {
                auth.logout();

                return;
            }
        }
    }
}
