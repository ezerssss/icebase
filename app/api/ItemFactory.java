package icebase.app.api;

import java.io.IOException;

import icebase.app.enums.fields.ITEM_FIELD;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.types.items.Food;
import icebase.app.types.items.Item;
import icebase.icebase.Doc;

public class ItemFactory {
    public static Item createItem(Doc doc) throws CategoryDoesNotExistException, IOException {
        String category = doc.data().split(",")[ITEM_FIELD.CATEGORY.index];

        switch (category) {
            case "food":
                return new Food(doc);

            default:
                throw new CategoryDoesNotExistException();
        }
    }
}
