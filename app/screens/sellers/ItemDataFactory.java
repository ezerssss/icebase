package icebase.app.screens.sellers;

import java.util.ArrayList;
import java.util.List;

import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.helpers.InputHelper;

public class ItemDataFactory {

        public static String[] createNewItem(CATEGORY_ENUM category) {
                List<String> data = new ArrayList<>();

                String name = InputHelper.getNonEmptyString("Enter item name: ", "\nItem name cannot be empty.\n");
                data.add(name);

                double price = InputHelper.getPositiveDouble("Enter item price: ",
                                "\nItem price cannot be 0 or negative. Please enter a valid amount.\n");
                data.add(Double.toString(price));

                int stock = InputHelper.getPositiveInt("Enter item stock: ",
                                "\nItem stock cannot be 0 or negative. Please enter a valid amount.\n");
                data.add(Integer.toString(stock));

                data.add(category.value);

                // NOTE: The order matters, it should follow the index indicated in the enum
                // ITEM_FIELD for each type of item
                switch (category) {
                        case FOOD:
                        case HOUSEHOLD_ITEMS:
                                // As you can see the SHELF_LIFE_INDEX is the last data according to the enum
                                // ITEM_FIELD found in Food.java, so it makes sense that it is the last data to
                                // be added in the data list
                                int shelfLife = InputHelper.getPositiveInt("Enter shelf life (in months): ",
                                                "\nShelf life cannot be 0 or negative.\n");
                                data.add(Integer.toString(shelfLife));

                                // Add more here if may new attributes, remember that order of adding to the
                                // list matters
                                break;
                        case ELECTRONICS:
                                int warranty = InputHelper.getPositiveInt("Enter warranty period (in months): ",
                                                "\nWarranty period cannot be 0 or negative.\n");
                                data.add(Integer.toString(warranty));
                                break;
                        case CLOTHING:
                                String size = InputHelper.getNonEmptyString("Enter size (S\\M\\L): ",
                                                "\nSize cannot be none.\n");
                                String material = InputHelper.getNonEmptyString("Enter material: ",
                                                "\nMaterial cannot be none.\n");
                                String color = InputHelper.getNonEmptyString("Enter color: ",
                                                "\nColor cannot be none.\n");

                                data.add(size);
                                data.add(material);
                                data.add(color);
                                break;
                        case HARDWARE:
                                String type = InputHelper.getNonEmptyString("Enter type (tool/accessory): ",
                                                "\nType cannot be none.\n");
                                data.add(type);
                                break;
                        default:
                                break;
                }

                String[] dataArray = new String[data.size()];
                data.toArray(dataArray);

                return dataArray;
        }
}
