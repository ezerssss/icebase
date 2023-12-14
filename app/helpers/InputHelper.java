package icebase.app.helpers;

import java.util.Scanner;

import icebase.app.App;
import icebase.app.MenuTitle;

public class InputHelper {
    public static int getChoiceInt(int choicesLength) {
        Scanner sc = App.sc;
        int choice;
        while (true) {
            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 0 && choice <= choicesLength) { // meaning within the options
                    break;
                }
                MenuTitle.printErrorMessage("Please choose from the given options...\n");
            } catch (NumberFormatException nf) {
                MenuTitle.printErrorMessage("Please input a valid number...\n");
            }
        }
        return choice;
    }

    public static String getNonEmptyString(String titleMessage, String errorMessage) {
        Scanner sc = App.sc;
        String value = "";

        while (value.isEmpty()) {
            System.out.print(titleMessage);

            value = sc.nextLine();

            if (value.isEmpty()) {
                MenuTitle.printErrorMessage(errorMessage);
            }
        }

        return value;
    }

    public static int getPositiveInt(String titleMessage, String errorMessage) {
        Scanner sc = App.sc;
        int value = 0;

        while (value <= 0) {
            try {
                System.out.print(titleMessage);
                String valueStr = sc.nextLine();

                if (valueStr.isEmpty()) {
                    MenuTitle.printErrorMessage(errorMessage);
                    continue;
                }

                value = Integer.parseInt(valueStr);

                if (value <= 0) {
                    MenuTitle.printErrorMessage(errorMessage);
                }
            } catch (NumberFormatException e) {
                MenuTitle.printErrorMessage("Invalid input. Please enter a valid numeric value.");
            }
        }

        return value;
    }

    public static double getPositiveDouble(String titleMessage, String errorMessage) {
        Scanner sc = App.sc;
        double value = 0;

        while (value <= 0) {
            try {
                System.out.print(titleMessage);
                String valueStr = sc.nextLine();

                if (valueStr.isEmpty()) {
                    MenuTitle.printErrorMessage(errorMessage);
                    continue;
                }

                value = Double.parseDouble(valueStr);

                if (value <= 0) {
                    MenuTitle.printErrorMessage(errorMessage);
                }
            } catch (NumberFormatException e) {
                MenuTitle.printErrorMessage("Invalid input. Please enter a valid numeric value.");
            }
        }

        return value;
    }
}
