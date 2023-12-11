package icebase.app;

import java.util.Scanner;

import icebase.icebase.User;
import icebase.icebase.Auth;

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

        // Pwede nyo i play around ang this shit
        try {
            Auth auth = Auth.getAuth();

            // setup sa account
            // might error if i run mo twice, so just delete lang sa users nga data folder
            User user = auth.signUp("hi", "hello");
            user.setStoreId("shitStoreID"); // generated random uid ni siya pero yeah
            user.setMoney(9999);

            // Actual logging in
            auth.login("hi", "hello");

            // Pwede na magamit anywhere sa app if need man niya ang signed in user
            user = auth.getUser();

            // everytime nga mag gamit sa currentUser sa auth, dapat i check muna if di siya
            // null, kay possible nga null siya
            if (user == null) {
                return;
            }

            System.out.println("Current user with id:" + user.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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