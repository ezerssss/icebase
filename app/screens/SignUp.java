package icebase.app.screens;

import icebase.app.App;
import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.api.API;
import icebase.app.enums.SCREEN_ENUM;
import icebase.icebase.Auth;
import icebase.icebase.User;
import icebase.icebase.exceptions.InvalidUsernameException;

import java.io.IOException;
import java.util.Scanner;

public class SignUp implements Screen {
    public void display() {
        Scanner sc = App.sc;
        while (true) {
            // handles exceptions
            try {
                MenuTitle.displayMainTitle();
                MenuTitle.displaySubTitle("Sign-up");

                Auth auth = Auth.getAuth();

                System.out.print("Enter a new username: ");
                String username = sc.nextLine();

                System.out.print("Enter a new password: ");
                String password = sc.nextLine();

                User user = auth.signUp(username, password);

                Router.navigate(SCREEN_ENUM.SET_UP, user);

                System.out.println("Sign-up successful!");
                break;

            } catch (InvalidUsernameException e) {
                System.out.println(
                        MenuTitle.getErrorMessage("Username already exists. Please choose a different username."));
            } catch (IOException e) {
                System.out.println(MenuTitle.getErrorMessage("Error occurred while signing up. Please try again."));
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(MenuTitle.getErrorMessage("An unexpected error occurred. Please try again."));
                e.printStackTrace();
            }
        }
    }
}
