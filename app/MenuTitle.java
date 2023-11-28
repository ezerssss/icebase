package icebase.app;

public class MenuTitle {
    public static final void displayMainTitle() {
        System.out.println(Colors.CYAN + """
                 _____  _____  ______  ____             _____  ______
                |_   _|/ ____||  ____||  _ \\    /\\     / ____||  ____|
                  | | | |     | |__   | |_) |  /  \\   | (___  | |__
                  | | | |     |  __|  |  _ <  / /\\ \\   \\___ \\ |  __|
                 _| |_| |____ | |____ | |_) |/ ____ \\  ____) || |____
                |_____|\\_____||______||____//_/    \\_\\|_____/ |______|""" + Colors.RESET);
    }

    public static final void displayBorder() {
        System.out.println(Colors.CYAN + "=======================================================" + Colors.RESET);
    }
}
