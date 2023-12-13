package icebase.app.screens;

import java.util.ArrayList;
import java.util.Scanner;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;

public class SplashScreen implements Screen {

    public void display() {
        Scanner sc = App.sc;
        int choice;
        ArrayList<String> options = new ArrayList<>();
        options.add("Log-in");
        options.add("Sign-up");
        options.add("Exit");

        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displayOptions(options);

            System.out.print("Choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    Router.navigate(SCREEN_ENUM.LOGIN);
                } else if (choice == 2) {
                    Router.navigate(SCREEN_ENUM.SIGN_UP);
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Please choose from the given options...\n");
                }
            } catch (Exception e) {
                System.out.println("Please choose from the given options...\n");
            }

        }
    }
}
