package icebase.icebase.exceptions;

import icebase.app.MenuTitle;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super(MenuTitle.getErrorMessage("User not found. Please sign-up first."));
    }
}
