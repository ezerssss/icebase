package icebase.app.screens.sellers;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;

import java.util.ArrayList;
import java.util.Scanner;

public class PostItem implements Screen {
    private ArrayList<String> categoryNames = new ArrayList<>();

    public PostItem() {
        for (CATEGORY_ENUM categoryENUM : CATEGORY_ENUM.values()) {
            categoryNames.add(categoryENUM.value);
        }
        categoryNames.add("Return");
    }

    public void display() {
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
