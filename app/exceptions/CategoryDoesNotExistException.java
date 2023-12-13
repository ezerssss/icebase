package icebase.app.exceptions;

import icebase.app.MenuTitle;

public class CategoryDoesNotExistException extends Exception {
    public CategoryDoesNotExistException() {
        String errorMessage = MenuTitle.displayErrorMessage("Category does not exist.");
        super(errorMessage);
    }
}
