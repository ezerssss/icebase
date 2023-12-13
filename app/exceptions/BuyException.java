package icebase.app.exceptions;

import icebase.app.MenuTitle;

public class BuyException extends Exception {
    public BuyException(String exception) {
        String errorMessage = MenuTitle.displayErrorMessage(exception);
        super(errorMessage);
    }
}
