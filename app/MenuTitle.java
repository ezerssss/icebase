package icebase.app;

import java.util.ArrayList;

public class MenuTitle {
    private static final int SCREEN_WIDTH = 55;
    private static String str;
    private static int strLength;
    private static int paddingLeft;

    public static final void displayMainBorder() {
        System.out.println(Colors.CYAN + "=======================================================" + Colors.RESET);
    }

    public static final void displaySubBorder() {
        System.out.println(Colors.CYAN + "-------------------------------------------------------" + Colors.RESET);
    }

    public static final void displayMainTitle() {
        System.out.println(Colors.CYAN + """
                 _____  _____  ______  ____             _____  ______
                |_   _|/ ____||  ____||  _ \\    /\\     / ____||  ____|
                  | | | |     | |__   | |_) |  /  \\   | (___  | |__
                  | | | |     |  __|  |  _ <  / /\\ \\   \\___ \\ |  __|
                 _| |_| |____ | |____ | |_) |/ ____ \\  ____) || |____
                |_____|\\_____||______||____//_/    \\_\\|_____/ |______|""" + Colors.RESET);
        displayMainBorder();
    }

    public static final void displaySubTitle(String title) {
        str = title.toUpperCase();
        strLength = str.length();
        paddingLeft = (SCREEN_WIDTH - strLength) / 2;
        System.out.printf("\n%" + paddingLeft + "s[%s]\n\n",
                " ", str);
    }

    public static final void displayStoreName(String name) {
        str = name.toUpperCase();
        strLength = str.length();
        paddingLeft = (SCREEN_WIDTH - strLength) / 2;
        int paddingRight = SCREEN_WIDTH - strLength - paddingLeft;

        System.out.println("");
        displayMainBorder();
        System.out.printf(Colors.CYAN + "=%" + (SCREEN_WIDTH - 2) + "s=\n" + Colors.RESET, " ");
        System.out.printf(Colors.CYAN + "=%" + (paddingLeft - 1) + "s%s%" + (paddingRight - 1) + "s=\n" + Colors.RESET,
                " ", str, " ");
        System.out.printf(Colors.CYAN + "=%" + (SCREEN_WIDTH - 2) + "s=\n" + Colors.RESET, " ");
        displayMainBorder();
    }

    public static final void displayOptions(ArrayList<String> options) {

        int optionLength;
        int maxOptionLength = options.get(0).length();
        for (String option : options) {
            optionLength = option.length();
            if (maxOptionLength < optionLength) {
                maxOptionLength = optionLength;
            }
        }

        paddingLeft = (SCREEN_WIDTH - (maxOptionLength + 4)) / 2;
        int size = options.size();
        System.out.println("");
        for (int i = 0; i < size; i++) {
            System.out.printf(
                    "%" + (paddingLeft) + "s[%d] %s\n", " ", i + 1, options.get(i));
        }
        System.out.println("");
    }

    public static final void printErrorMessage(String errorMessage) {
        System.out.println(Colors.RED + errorMessage + Colors.RESET);
    }

    public static final void printSuccessMessage(String successMessage) {
        System.out.println(Colors.CYAN + "\n" + successMessage + Colors.RESET);
    }
}
