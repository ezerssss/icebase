package icebase.app.screens;
import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.api.API;
import icebase.icebase.Auth;
import icebase.icebase.User;
import icebase.icebase.exceptions.InvalidUsernameException;

import java.io.IOException;
import java.util.Scanner;

public class SignUp implements Screen {
    public void display() {
        Scanner sc = App.sc;
        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("[Sign-up]");

            Auth auth = Auth.getAuth();

            System.out.print("Enter a new username: ");
            String username = sc.nextLine();

            System.out.print("Enter a new password: ");
            String password = sc.nextLine();

            // handles exceptions
            try {

                User user = auth.signUp(username, password);

                System.out.println();
                MenuTitle.displayMainTitle();
                MenuTitle.displaySubTitle("[Set-up]");

                String storeName = null;
                while (storeName == null || storeName.isEmpty()) {
                    System.out.print("Enter your store name: ");
                    storeName = sc.nextLine();

                    if (storeName.isEmpty()) {
                        System.out.println("\nStore name cannot be empty. Please enter a valid store name.\n");
                    }
                }

                Double startingMoney = null;
                while (startingMoney == null || startingMoney < 0) {
                    try {
                        System.out.print("\nEnter your starting money: ");
                        String moneyInputStr = sc.nextLine();
                
                        if (moneyInputStr.isEmpty()) {
                            System.out.println("Starting money cannot be empty. Please enter a valid amount.");
                            continue;
                        }
                        
                        // parse to double to check if negative
                        double moneyInput = Double.parseDouble(moneyInputStr);
                
                        if (moneyInput < 0) {
                            System.out.println("Starting money cannot be negative. Please enter a positive amount.");
                            continue;
                        }
                        startingMoney = moneyInput;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric value for starting money.");
                    }
                }
                
                API.setupUser(user, storeName, startingMoney);

                System.out.println("Sign-up successful!");
                break;
                
            } catch (InvalidUsernameException e) {
                System.out.println("Username already exists. Please choose a different username.");
            } catch (IOException e) {
                System.out.println("Error occurred while signing up. Please try again.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                e.printStackTrace();
            }
        }
    }
}
