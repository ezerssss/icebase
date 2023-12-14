package icebase.icebase.exceptions;

import icebase.app.MenuTitle;

public class InvalidUsernameException extends Exception {

    public InvalidUsernameException() {
        super(MenuTitle.getErrorMessage("Username already taken."));
    }
}
