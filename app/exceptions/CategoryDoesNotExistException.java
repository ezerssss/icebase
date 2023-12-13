package icebase.app.exceptions;

import icebase.app.MenuTitle;

public class CategoryDoesNotExistException extends Exception {
    public CategoryDoesNotExistException() {
        super(MenuTitle.getErrorMessage("Category does not exist."));
    }
}
