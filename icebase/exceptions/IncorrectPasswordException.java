package icebase.icebase.exceptions;

import icebase.app.MenuTitle;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException() {
        super(MenuTitle.getErrorMessage("Password incorrect."));
    }
}
