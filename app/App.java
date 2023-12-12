package icebase.app;

import java.util.Scanner;

import icebase.app.screens.SplashScreen;

public class App {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen();
        splash.display();

        sc.close();
    }
}