package icebase.app.screens.sellers;

import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;
import icebase.app.types.Store;
import icebase.icebase.Auth;
import icebase.icebase.User;

import java.util.ArrayList;

public class SellingView implements Screen {
    private ArrayList<String> categoryNames = new ArrayList<>();
    private Store store;
    private String storeName;

    public SellingView(Store store) {
        for (CATEGORY_ENUM categoryENUM : CATEGORY_ENUM.values()) {
            categoryNames.add(categoryENUM.value);
        }
        categoryNames.add("Return");

        this.store = store;
        this.storeName = this.store.getName();
    }

    public void display() {
        MenuTitle.displayStoreName(storeName);
        MenuTitle.displaySubTitle("SELL NEW ITEM");
        MenuTitle.displayOptions(categoryNames);
        int choice = InputHelper.getChoiceInt(categoryNames.size());
        if (choice == categoryNames.size()) {
            return;
        }
        CATEGORY_ENUM category = CATEGORY_ENUM.values()[choice - 1];

        try {
            String[] itemData = ItemDataFactory.createNewItem(category);

            API.sell(itemData);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
