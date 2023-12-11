package icebase.app.screens.home;

import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.screens.Screen;

public class Home implements Screen {
    public void display() {
        Scanner sc = App.sc;
        int choice;
        String[] options = { "Visit Stores", "Item Search", "Exit" };

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);
            System.out.print("Choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    Router.navigate(SCREEN_ENUM.STORE_LIST);
                } else if (choice == 2) {
                    Router.navigate(SCREEN_ENUM.GLOBAL_SEARCH);
                } else if (choice == 3) {
                    return;
                } else {
                    System.out.println("Please choose from the given options...\n");
                }
            } catch (Exception e) {
                System.out.println("Please choose from the given options...\n");
            }
        }
    }
}
