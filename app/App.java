package icebase.app;

import java.util.Scanner;

import icebase.icebase.Icebase;

public class App {

    public static void main(String[] args) {
        Icebase ib = new Icebase();
        Scanner sc = new Scanner(System.in);

        // Splash Screen
        MenuTitle.displayMainTitle();
        MenuTitle.displayBorder();
        System.out.printf("\n\t[1] %s%15s[2] %s\n\n",
                "Log-in", " ", "Sign-up");
        System.out.print("Choice: ");
        sc.nextLine();
    }
}