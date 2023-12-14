package icebase.app.screens;

import icebase.app.Colors;
import icebase.app.MenuTitle;
import icebase.app.Router;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.icebase.Auth;
import icebase.icebase.User;
import icebase.icebase.exceptions.InvalidUsernameException;

import java.io.IOException;

public class SignUp implements Screen {
    public void display() {
        while (true) {
            // handles exceptions
            try {
                MenuTitle.displayMainTitle();
                MenuTitle.displaySubTitle("Sign-up");

                Auth auth = Auth.getAuth();

                String username = InputHelper.getNonEmptyString("Enter a new username: ", "Username cannot be empty.");

                String password = InputHelper.getNonEmptyString("Enter a new password: ", "Password cannot be empty.");

                User user = auth.signUp(username, password);

                Router.navigate(SCREEN_ENUM.SET_UP, user);

                System.out.println(Colors.CYAN + "\nSign-up successful!" + Colors.RESET);
                break;

            } catch (InvalidUsernameException e) {
                System.out.println(
                        MenuTitle.getErrorMessage("Username already exists. Please choose a different username."));
            } catch (IOException e) {
                MenuTitle.printErrorMessage("Error occurred while signing up. Please try again.");
                e.printStackTrace();
            } catch (Exception e) {
                MenuTitle.printErrorMessage("An unexpected error occurred. Please try again.");
                e.printStackTrace();
            }
        }
    }
}
