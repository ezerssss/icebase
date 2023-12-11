package icebase.app.screens;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.icebase.Auth;
import icebase.icebase.exceptions.InvalidUsernameException;

import java.io.IOException;
import java.util.Scanner;

public class SignUp implements Screen {
    public void display() {
        Scanner sc = App.sc;
        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("[Sign-up]");

            try {
                Auth auth = Auth.getAuth();

                System.out.print("Enter a new username: ");
                String username = sc.nextLine();

                System.out.print("Enter a new password: ");
                String password = sc.nextLine();

                // handles exceptions
                try {
                    auth.signUp(username, password);
                    System.out.println("Sign-up successful!");
                    break; 
                } catch (InvalidUsernameException e) {
                    System.out.println("Username already exists. Please choose a different username.");
                } catch (IOException e) {
                    System.out.println("Error occurred while signing up. Please try again.");
                    e.printStackTrace(); 
                }

            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                e.printStackTrace(); 
            }
        }
    }
}