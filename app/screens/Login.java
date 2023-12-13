package icebase.app.screens;

import java.util.Scanner;

import icebase.app.*;
import icebase.app.enums.SCREEN_ENUM;
import icebase.icebase.Auth;
import icebase.icebase.User;
import icebase.icebase.exceptions.UserNotFoundException;

public class Login implements Screen {

    public void display() {
        Scanner sc = App.sc;
        Auth auth = Auth.getAuth();
        User user;

        // Need while loop for error messages
        while (true) {
            MenuTitle.displayMainTitle();
            MenuTitle.displaySubTitle("Log-in");
            try {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();

                user = auth.login(username, password);

                if (user != null) {
                    break;
                }
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        Router.navigate(SCREEN_ENUM.HOME);
    }
}
