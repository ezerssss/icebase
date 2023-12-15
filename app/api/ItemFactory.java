package icebase.app.api;

import java.io.IOException;

import icebase.app.enums.fields.ITEM_FIELD;
import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.types.items.*;
import icebase.icebase.Doc;

public class ItemFactory {
    public static Item createItem(Doc doc) throws CategoryDoesNotExistException, IOException {
        String category = doc.data().split(",")[ITEM_FIELD.CATEGORY.index];
        switch (category) {
            case "Food":
                return new Food(doc);
            case "Household Items":
                return new HouseholdItems(doc);
            case "Electronics":
                return new Electronics(doc);
            case "Clothing":
                return new Clothing(doc);
            case "Hardware":
                return new Hardware(doc);
            default:
                throw new CategoryDoesNotExistException();
        }
    }
}
