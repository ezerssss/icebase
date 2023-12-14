package icebase.app.screens;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.app.helpers.InputHelper;
import icebase.icebase.Auth;
import icebase.icebase.User;
import icebase.icebase.exceptions.UserNotFoundException;

public class Login implements Screen {

    public void display() {
        Auth auth = Auth.getAuth();
        User user;

        // Need while loop for error messages
        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("Log-in");
            try {
                String username = InputHelper.getNonEmptyString("Username: ", "Username cannot be empty.");
                String password = InputHelper.getNonEmptyString("Password: ", "Password cannot be empty.");

                user = auth.login(username, password);

                if (user != null) {
                    break;
                }
            } catch (UserNotFoundException e) {
                MenuTitle.printErrorMessage(e.getMessage());
                return;
            } catch (Exception e) {
                MenuTitle.printErrorMessage(e.getMessage());
            }
        }

        Router.navigate(SCREEN_ENUM.HOME);
    }
}
