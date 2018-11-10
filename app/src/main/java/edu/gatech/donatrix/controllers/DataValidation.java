package edu.gatech.donatrix.controllers;

import java.util.regex.Pattern;

public class DataValidation {
    public static boolean isEmailValid(String email) {
        Pattern validEmail = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+$");
        return validEmail.matcher(email).matches();
    }

    public static boolean isPasswordStrong(String pass) {
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
