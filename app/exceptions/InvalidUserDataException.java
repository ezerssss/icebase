package icebase.app.exceptions;

import icebase.app.MenuTitle;

public class InvalidUserDataException extends Exception {

    public InvalidUserDataException() {
        super(MenuTitle.getErrorMessage("User has invalid data."));
    }
}
