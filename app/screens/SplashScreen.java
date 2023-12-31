package icebase.app.screens;

import java.util.ArrayList;
import java.util.Scanner;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;

public class SplashScreen implements Screen {

    public void display() {
        int choice;

        ArrayList<String> options = new ArrayList<>();
        options.add("Log-in");
        options.add("Sign-up");
        options.add("Exit");

        int range = options.size();

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);

            choice = InputHelper.getChoiceInt(range);
            if (choice == 1) {
                Router.navigate(SCREEN_ENUM.LOGIN);
            } else if (choice == 2) {
                Router.navigate(SCREEN_ENUM.SIGN_UP);
            } else {
                break;
            }
        }
    }
}
