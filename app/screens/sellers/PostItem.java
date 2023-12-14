package icebase.app.screens.sellers;

import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.exceptions.UnauthorizedException;
import icebase.app.helpers.InputHelper;
import icebase.app.screens.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class PostItem implements Screen {
    private ArrayList<String> categoryNames = new ArrayList<>();

    public PostItem() {
        for (CATEGORY_ENUM categoryENUM : CATEGORY_ENUM.values()) {
            categoryNames.add(categoryENUM.value.toUpperCase());
        }
        categoryNames.add("Return");
    }

    public void display() {
        MenuTitle.displayStoreName("SELL A NEW ITEM");
        MenuTitle.displaySubTitle("CHOOSE CATEGORY");
        MenuTitle.displayOptions(categoryNames);
        int choice = InputHelper.getChoiceInt(categoryNames.size());
        if (choice == categoryNames.size()) {
            return;
        }
        CATEGORY_ENUM category = CATEGORY_ENUM.values()[choice - 1];

        try {
            String[] itemData = ItemDataFactory.createNewItem(category);

            API.sell(itemData);
        } catch (UnauthorizedException e) {
            MenuTitle.printErrorMessage(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            MenuTitle.printErrorMessage("An unexpected error occurred.");

            e.printStackTrace();
        }
    }
}
