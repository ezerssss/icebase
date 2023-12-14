package icebase.app.screens.sellers;

import java.util.ArrayList;

import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;
import icebase.app.types.Store;

public class SellingView implements Screen {
    private Store store;
    private String storeName;

    public SellingView(Store store) {
        this.store = store;
        this.storeName = this.store.getName();
    }

    public void display() {
        MenuTitle.displayStoreName(storeName);
        ArrayList<String> options = new ArrayList<>();
        options.add("Post Item");
        options.add("Manage Items");
        options.add("Exit Store");

        int range = options.size();
        int choice;

        while (true) {
            MenuTitle.displayOptions(options);

            choice = InputHelper.getChoiceInt(range);
            if (choice == 1) {
                Router.navigate(SCREEN_ENUM.POST_ITEM);
            } else if (choice == 2) {
                Router.navigate(SCREEN_ENUM.SELLING_ITEM_LIST);
            } else {
                return;
            }
        }
    }
}
