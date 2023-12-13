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
                System.out.println("Please choose from the given options...\n");
            } catch (NumberFormatException nf) {
                System.out.println("Please input a valid number...\n");
            }
        }
        return choice;
    }
}
