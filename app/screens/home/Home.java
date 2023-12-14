package icebase.app.screens.home;

import java.util.ArrayList;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;

public class Home implements Screen {
    public void display() {
        int choice;
        ArrayList<String> options = new ArrayList<>();
        options.add("Visit Stores");
        options.add("Item Search");
        options.add("Log-out");

        int range = options.size();

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);

            choice = InputHelper.getChoiceInt(range);
            if (choice == 1) {
                Router.navigate(SCREEN_ENUM.STORE_LIST);
            } else if (choice == 2) {
                Router.navigate(SCREEN_ENUM.GLOBAL_SEARCH);
            } else {
                return;
            }
        }
    }
}
