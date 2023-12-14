package icebase.icebase.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found. Please sign-up first.");
    }
}
