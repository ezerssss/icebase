package icebase.app.exceptions;

import icebase.app.MenuTitle;

public class InvalidUserDataException extends Exception {
    public InvalidUserDataException() {
        String errorMessage = MenuTitle.displayErrorMessage("User has invalid data.");
        super(errorMessage);
    }
}
