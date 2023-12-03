package icebase.app;

import java.util.Scanner;

import icebase.icebase.Icebase;

public class App {

        public static void main(String[] args) {
                Icebase ib = new Icebase();
                Scanner sc = new Scanner(System.in);

                // Splash Screen
                MenuTitle.displayMainTitle();
                System.out.printf("\n\t[1] %s%15s[2] %s\n\n",
                                "Log-in", " ", "Sign-up");
                System.out.print("Choice: ");
                sc.nextLine();

                // Log in Screen
                MenuTitle.displayMainTitle();
                MenuTitle.displaySubTitle("[Log-in]");
                System.out.print("Username: ");
                sc.nextLine();
                System.out.print("Password: ");
                sc.nextLine();

                // Home
                MenuTitle.displayMainTitle();
                System.out.println(
                                "\n\t\t[1] - Visit Stores\n\t\t[2] - Item Search\n");
                System.out.print("Choice: ");
                sc.nextLine();

                // Visit Stores Page
                MenuTitle.displayMainTitle();
                MenuTitle.displaySubTitle("[STORES]");
                System.out.println(
                                "\t\tStore 1\n\t\tStore 2\n\t\t...\n");
                System.out.print("Choice: ");
                sc.nextLine();

                // Buying View
                MenuTitle.displayStoreName("storename");
                System.out.println(
                                "\n\t\t[1] - Category\n\t\t[2] - View Cart\n\t\t[3] - Check-out\n\t\t[4] - Exit Store\n");
                System.out.print("Choice: ");
                sc.nextLine();

        }
}