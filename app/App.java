package icebase.app;

import java.util.Scanner;

import icebase.icebase.User;
import icebase.app.screens.SplashScreen;
import icebase.icebase.Auth;

public class App {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        SplashScreen splash = new SplashScreen();
        splash.display();

        sc.close();
    }
}