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
        String[] storeList = { "Store 1", "Store 2" };
        int range = storeList.length;
        storeList[range - 1] = "Return";
        int choice;

        MenuTitle.displayOptions(storeList);
        // try {
        // System.out.print("Choice: ");
        // choice = Integer.parseInt(sc.nextLine());
        // BuyingView(store);
        // }
    }
}
