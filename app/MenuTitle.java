package icebase.app;

import java.util.ArrayList;;

public class MenuTitle {
    public static final void displayBorder() {
        System.out.println(Colors.CYAN + "=======================================================" + Colors.RESET);
    }

    public static final void displayMainTitle() {
        System.out.println(Colors.CYAN + """
                 _____  _____  ______  ____             _____  ______
                |_   _|/ ____||  ____||  _ \\    /\\     / ____||  ____|
                  | | | |     | |__   | |_) |  /  \\   | (___  | |__
                  | | | |     |  __|  |  _ <  / /\\ \\   \\___ \\ |  __|
                 _| |_| |____ | |____ | |_) |/ ____ \\  ____) || |____
                |_____|\\_____||______||____//_/    \\_\\|_____/ |______|""" + Colors.RESET);
        displayBorder();
    }

    public static final void displaySubTitle(String subTitle) {
        System.out.printf("\n\t%15s[%s]%15s\n\n",
                " ", subTitle, " ");
    }

    public static final void displayStoreName(String name) {
        String storeName = name.toUpperCase();
        int nameLength = storeName.length();
        int paddingLeft = (53 - nameLength) / 2;
        int paddingRight = 53 - nameLength - paddingLeft;
        displayBorder();
        System.out.printf(Colors.CYAN + "=%53s=\n" + Colors.RESET,
                " ");
        System.out.printf(Colors.CYAN + "=%" + paddingLeft + "s%s%" + paddingRight + "s=\n" + Colors.RESET,
                " ", storeName, " ");
        System.out.printf(Colors.CYAN + "=%53s=\n" + Colors.RESET,
                " ");
        MenuTitle.displayBorder();
    }

    public static final void displayOptions(ArrayList<String> options) {
        int size = options.size();
        System.out.println("");
        for (int i = 0; i < size; i++) {
            System.out.printf(
                    "\t%13s[%d] %s\n", " ", i + 1, options.get(i));
        }
        System.out.println("");
    }

    public static final String getErrorMessage(String errorMessage) {
        return (Colors.RED + errorMessage + Colors.RESET);
    }
}
