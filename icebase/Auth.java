package icebase.icebase;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import icebase.icebase.exceptions.IncorrectPasswordException;
import icebase.icebase.exceptions.InvalidUsernameException;
import icebase.icebase.exceptions.UserNotFoundException;

public class Auth {
    private static Auth auth;
    public static final String USERS_PATH = String.join(File.separator, Db.DATA_ROOT_PATH, "users");
    private static final Collection USERS_COLLECTION = new Collection("users");
    private static final int USER_ID = 0;
    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;
    private static final int STORE_ID = 3;
    private static final int MONEY = 4;
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
        List<Doc> docs = USERS_COLLECTION.where(username, USERNAME);

        if (docs.isEmpty()) {
            throw new UserNotFoundException();
        }

        // Get the first value of the list, we can make sure that this is what we want
        // since we don't allow same usernames
        Doc userDoc = docs.get(0);
        String[] data = userDoc.data().split(",");

        if (!data[PASSWORD].equals(password)) {
            throw new IncorrectPasswordException();
        }

        User user = new User(data[USER_ID], data[USERNAME], data[PASSWORD], userDoc);
        user.setStoreId(data[STORE_ID]);
        user.setMoney(Double.parseDouble(data[MONEY]));

        this.currentUser = user;

        return user;
    }

    public User signUp(String username, String password) throws InvalidUsernameException, IOException {
        // Check if username already exists
        List<Doc> docs = USERS_COLLECTION.where(username, USERNAME);

        if (!docs.isEmpty()) {
            throw new InvalidUsernameException();
        }

        String userId = UUID.randomUUID().toString();

        Doc userDoc = new Doc(USERS_COLLECTION, userId);
        String userData = String.join(",", userId, username, password);

        // saves the user to the database
        USERS_COLLECTION.setDoc(userDoc, userData);

        return new User(userId, username, password, userDoc);
    }
}
