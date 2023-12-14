package icebase.app.exceptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super(
                "You do not have the necessary permissions to access this resource. Please login to the app.");
    }
}
