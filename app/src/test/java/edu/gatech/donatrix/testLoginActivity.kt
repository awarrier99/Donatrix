package edu.gatech.donatrix

import org.junit.Test

import java.util.HashMap

import edu.gatech.donatrix.model.User

class testLoginActivity {

    @Test
    fun testUserLogin() {
        val body = HashMap<String, Any>()
        body["email"] = "zday123@yahoo.com"
        body["password"] = "janisday"
        //        assertEquals("User sign in correct", User.testLoggedIn(body), true);
    }
}