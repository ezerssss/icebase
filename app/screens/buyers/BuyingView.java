package icebase.app.screens.buyers;

import java.util.ArrayList;

import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
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
        categoryNames.add("All");
        categoryNames.add("Return");
    }

    public void display() {
        CATEGORY_ENUM category;
        int choice;

        while (true) {
            MenuTitle.displayStoreName(storeName);
            MenuTitle.displaySubTitle("CATEGORIES");
            MenuTitle.displayOptions(categoryNames);
            choice = InputHelper.getChoiceInt(categoryNames.size());
            if (choice == categoryNames.size()) {
                return;
            }

            // If user selects "All"
            if (choice == categoryNames.size() - 1) {
                Router.navigate(SCREEN_ENUM.BUYING_ITEM_LIST, store);
            } else {
                category = CATEGORY_ENUM.values()[choice - 1];
                Router.navigate(SCREEN_ENUM.BUYING_ITEM_LIST, store, category);
            }

        }
    }
}
