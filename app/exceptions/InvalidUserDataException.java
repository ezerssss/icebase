package icebase.app.exceptions;

public class InvalidUserDataException extends Exception {
    public InvalidUserDataException() {
        super("User has invalid data.");
    }
}
