package icebase.app.screens.home;

import java.util.Scanner;
import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.screens.Screen;

public class StoreList implements Screen {
    public void display() {
        Scanner sc = App.sc;

        MenuTitle.displayMainTitle();
        MenuTitle.displaySubTitle("[STORES]");
        System.out.println(
                "\t\tStore 1\n\t\tStore 2\n\t\t...\n");
        System.out.print("Choice: ");
        sc.nextLine();
    }
}
