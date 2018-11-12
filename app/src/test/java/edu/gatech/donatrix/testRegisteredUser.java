package edu.gatech.donatrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.model.User;
import edu.gatech.donatrix.model.UserType;

public class testRegisteredUser {
    final Database db = Database.getInstance();
    User good;
    User locked;
    User nUser;
    User unregistered;
    User badUser;

    @Before
    public void setUp() {
        good = new User("zday123@yahoo.com", "janisday", "Zeke Day", false, UserType.USER);
        locked = new User("aday123@yahoo.com", "password", "Alex Day", true, UserType.USER);
        unregistered = new User("blah@blah.com", "blah", "Blah", false, UserType.USER);
        nUser = new User(null, "null", "Null", false, UserType.USER);
        badUser = new User("zday123@yahoo.com", "password", "Zeke Day", false, UserType.USER);
        db.registerUser(good);
        db.registerUser(locked);
        db.registerUser(nUser);
    }

    @Test
    public void testUnregisteredUser() {
        assertFalse(db.checkRegisteredUser(unregistered.getEmail(), unregistered.getPassword()));
    }

    @Test
    public void testLockedUser() {
        assertFalse(db.checkRegisteredUser(locked.getEmail(), locked.getPassword()));
    }

    @Test
    public void testNullUser() {
        assertFalse(db.checkRegisteredUser(nUser.getEmail(), nUser.getPassword()));
    }

    @Test
    public void testBadPassword() {
        assertFalse(db.checkRegisteredUser(badUser.getEmail(), badUser.getPassword()));
    }

    @Test
    public void testGoodUser() {
        assertTrue(db.checkRegisteredUser(good.getEmail(), good.getPassword()));
    }

    @After
    public void tearDown() {
        db.unregisterUser(good);
        db.unregisterUser(locked);
        db.unregisterUser(nUser);
    }
}