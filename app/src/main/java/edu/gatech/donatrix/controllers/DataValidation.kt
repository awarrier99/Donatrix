package edu.gatech.donatrix.controllers

import java.util.regex.Pattern

object DataValidation {
    fun isEmailValid(email: String): Boolean {
        val validEmail = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+$")
        return validEmail.matcher(email).matches()
    }

    fun isPasswordStrong(pass: String): Boolean {
        val upper = Pattern.compile("[A-Z]")
        val lower = Pattern.compile("[a-z]")
        val num = Pattern.compile("[0-9]")

        val hasUpper = upper.matcher(pass).find()
        val hasLower = lower.matcher(pass).find()
        val hasNum = num.matcher(pass).find()
        val isLong = pass.length >= 8

        return hasUpper && hasLower && hasNum && isLong
    }
}
