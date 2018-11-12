package edu.gatech.donatrix.controllers;

import java.util.regex.Pattern;

/**
 * One of Paul's Testers
 */
public class DataValidation {
    /**
     * A method to check if Email is valid
     * @param email The email to check
     * @return If it is valid or not
     */
    public static boolean isEmailValid(CharSequence email) {
        Pattern validEmail = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+$");
        return validEmail.matcher(email).matches();
    }

    /**
     * To check if password is strong. A-Z. a-z. 0-9.
     * @param pass The password to test.
     * @return true if strong.
     */
    public static boolean isPasswordStrong(CharSequence pass) {
        Pattern upper = Pattern.compile("[A-Z]");
        Pattern lower = Pattern.compile("[a-z]");
        Pattern num = Pattern.compile("[0-9]");

        boolean hasUpper = upper.matcher(pass).find();
        boolean hasLower = lower.matcher(pass).find();
        boolean hasNum = num.matcher(pass).find();
        boolean isLong = pass.length() >= 8;

        return hasUpper && hasLower && hasNum && isLong;
    }
}
