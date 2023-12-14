package icebase.app.screens.home;

import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.screens.Screen;

public class Home implements Screen {
    public void display() {
        Scanner sc = App.sc;
        int choice;
        ArrayList<String> options = new ArrayList<>();
        options.add("Visit Stores");
        options.add("Item Search");
        options.add("Log-out");

        int range = options.size();

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);

            try {
                choice = ChoiceHelper.getChoice(range);
                if (choice == 1) {
                    Router.navigate(SCREEN_ENUM.STORE_LIST);
                } else if (choice == 2) {
                    Router.navigate(SCREEN_ENUM.GLOBAL_SEARCH);
                } else {
                    return;
                }
            } catch (Exception e) {
                MenuTitle.printErrorMessage(e.getMessage());
            }
        }
    }
}
