package edu.gatech.donatrix.dao;

import android.content.Context;

import edu.gatech.donatrix.model.User;

/**
 * Database access object
 */
class UserDao {
    /**
     * Registers a user
     *
     * @param user the user to register
     * @param context the current context
     * @throws IllegalArgumentException thrown if arguments are invalid
     */
    public static void registerUser(User user, Context context) throws IllegalArgumentException {
        Database.getInstance(context).registerUser(user, context);
    }

    /**
     * checks if a user is registered
     *
     * @param email the email of the user
     * @param password the user's password
     * @param context the current context
     * @return true if successful
     */
    public static boolean checkRegisteredUser(String email, String password, Context context) {
        return Database.getInstance(context).checkRegisteredUser(email, password);
    }

    /**
     * Gets a user
     *
     * @param email the email of the user
     * @param context the current context
     *
     * @return the user
     */
    public static User getUser(String email, Context context) {
        return Database.getInstance(context).getUser(email);
    }
}
