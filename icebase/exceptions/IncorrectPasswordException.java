package icebase.icebase.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Password incorrect.");
    }
}
