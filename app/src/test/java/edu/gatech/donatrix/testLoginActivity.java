package edu.gatech.donatrix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.donatrix.model.User;

import static org.junit.Assert.*;

public class testLoginActivity {

    @Test
    public void testUserLogin() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "zday123@yahoo.com");
        body.put("password", "janisday");
        System.out.println(User.testLoggedIn(body));
//        assertEquals("User sign in correct", User.testLoggedIn(body), true);
    }
}