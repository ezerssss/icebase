package icebase.app;

import java.util.Scanner;

public class ChoiceHelper {
    public static int getChoice(int choicesLength) {
        Scanner sc = App.sc;
        int choice;
        while (true) {
            System.out.print("\nChoice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice > 0 && choice <= choicesLength) { // meaning within the options
                    break;
                }
                MenuTitle.printErrorMessage("Please choose from the given options...");
            } catch (NumberFormatException nf) {
                MenuTitle.printErrorMessage("Please input a valid number...");
            }
        }
        return choice;
    }
}
