package icebase.app.exceptions;

public class CategoryDoesNotExistException extends Exception {
    public CategoryDoesNotExistException() {
        super("Category does not exist.");
    }
}
