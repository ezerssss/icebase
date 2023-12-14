package icebase.app.screens.sellers;

import java.util.ArrayList;
import java.util.List;

import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.helpers.InputHelper;

public class ItemDataFactory {

    public static String[] createNewItem(CATEGORY_ENUM category) {
        List<String> data = new ArrayList<>();

        String name = InputHelper.getNonEmptyString("Enter item name: ", "Item name cannot be empty.");
        data.add(name);

        double price = InputHelper.getPositiveDouble("Enter item price: ",
                "Item price cannot be 0 or negative. Please enter a valid amount.");
        data.add(Double.toString(price));

        int stock = InputHelper.getPositiveInt("Enter item stock: ",
                "Item stock cannot be 0 or negative. Please enter a valid amount.");
        data.add(Integer.toString(stock));

        data.add(category.value);

        // NOTE: The order matters, it should follow the index indicated in the enum
        // ITEM_FIELD for each type of item
        switch (category) {
            case FOOD:
                // As you can see the SHELF_LIFE is the last data according to the enum
                // ITEM_FIELD found in Food.java, so it makes sense that it is the last data to
                // be added in the data list
                int shelfLife = InputHelper.getPositiveInt("Enter food shelf life: ",
                        "Food shelf life cannot be 0 or bitches.");
                data.add(Integer.toString(shelfLife));

                // Add more here if may new attributes, remember that order of adding to the
                // list matters
                break;

            default:
                break;
        }

        String[] dataArray = new String[data.size()];
        data.toArray(dataArray);

        return dataArray;
    }
}
