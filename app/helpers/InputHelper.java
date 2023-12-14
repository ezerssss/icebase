package icebase.app.helpers;

import java.util.Scanner;

import icebase.app.App;

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
                System.out.println("Please choose from the given options...\n");
            } catch (NumberFormatException nf) {
                System.out.println("Please input a valid number...\n");
            }
        }
        return choice;
    }

    public static String getNonEmptyString(String titleMessage, String errorMessage) {
        Scanner sc = App.sc;
        String value = "";

        while (value.isEmpty()) {
            System.out.println(titleMessage);

            value = sc.nextLine();

            if (value.isEmpty()) {
                System.out.println(errorMessage);
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
                    System.out.println(errorMessage);
                    continue;
                }

                value = Integer.parseInt(valueStr);

                if (value <= 0) {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric value.");
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
                    System.out.println(errorMessage);
                    continue;
                }

                value = Double.parseDouble(valueStr);

                if (value <= 0) {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric value.");
            }
        }

        return value;
    }
}
