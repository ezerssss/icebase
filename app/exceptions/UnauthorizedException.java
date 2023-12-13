package icebase.app.exceptions;

import icebase.app.MenuTitle;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super(MenuTitle.getErrorMessage(
                "You do not have the necessary permissions to access this resource. Please login to the app."));
    }
}
