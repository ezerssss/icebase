package icebase.app.screens.buyers;

import icebase.app.App;
import icebase.app.ChoiceHelper;

import java.util.ArrayList;
import java.util.Scanner;

import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.screens.Screen;
import icebase.app.types.Store;

public class BuyingView implements Screen {
    private String storeName;
    private Store store;
    private ArrayList<String> categoryNames = new ArrayList<>();

    public BuyingView(Store store) {
        this.store = store;
        this.storeName = store.getName();
        for (CATEGORY_ENUM categoryENUM : CATEGORY_ENUM.values()) {
            categoryNames.add(categoryENUM.value);
        }
        categoryNames.add("Return");
    }

    public void display() {
        CATEGORY_ENUM category;
        int choice;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("CATEGORIES");
            MenuTitle.displayOptions(categoryNames);
            choice = ChoiceHelper.getChoice(categoryNames.size());
            if (choice == categoryNames.size()) {
                return;
            }
            category = CATEGORY_ENUM.values()[choice - 1];
            Router.navigate(SCREEN_ENUM.BUYING_ITEM_LIST, store, category);
        }
    }
}
