package icebase.icebase;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import icebase.icebase.enums.USER_FIELD;
import icebase.icebase.exceptions.IncorrectPasswordException;
import icebase.icebase.exceptions.InvalidUsernameException;
import icebase.icebase.exceptions.UserNotFoundException;

public class Auth {
    private static Auth auth;
    public static final String USERS_PATH = String.join(File.separator, Db.DATA_ROOT_PATH, "users");
    private static final Collection USERS_COLLECTION = Db.getDb().collection("users");
    private User currentUser;

    public static Auth getAuth() {
        if (auth == null) {
            auth = new Auth();
        }

        return auth;
    }

    public User getUser() {
        return this.currentUser;
    }

    public User login(String username, String password)
            throws UserNotFoundException, IncorrectPasswordException, IOException,
            ArrayIndexOutOfBoundsException, NumberFormatException {
        QueryList docs = USERS_COLLECTION.where(username, USER_FIELD.USERNAME.index);

        if (docs.isEmpty()) {
            throw new UserNotFoundException();
        }

        // Get the first value of the list, we can make sure that this is what we want
        // since we don't allow same usernames
        Doc userDoc = docs.get(0);
        String[] data = userDoc.data().split(",");

        if (!data[USER_FIELD.PASSWORD.index].equals(password)) {
            throw new IncorrectPasswordException();
        }

        User user = new User(userDoc);
        this.currentUser = user;

        return user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User signUp(String username, String password) throws InvalidUsernameException, IOException {
        // Check if username already exists
        QueryList docs = USERS_COLLECTION.where(username, USER_FIELD.USERNAME.index);

        if (!docs.isEmpty()) {
            throw new InvalidUsernameException();
        }

        String userId = UUID.randomUUID().toString();

        Doc userDoc = new Doc(USERS_COLLECTION, userId);
        String userData = String.join(",", userId, username, password);

        // saves the user to the database
        USERS_COLLECTION.setDoc(userDoc, userData);

        return new User(userDoc);
    }
}
