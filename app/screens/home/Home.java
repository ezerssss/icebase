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

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);
            System.out.print("Choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        Router.navigate(SCREEN_ENUM.STORE_LIST);
                        break;
                    case 2:
                        Router.navigate(SCREEN_ENUM.GLOBAL_SEARCH);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Please choose from the given options...\n");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
