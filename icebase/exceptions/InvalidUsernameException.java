package icebase.icebase.exceptions;

public class InvalidUsernameException extends Exception {

    public InvalidUsernameException() {
        super("Username already taken.");
    }
}
