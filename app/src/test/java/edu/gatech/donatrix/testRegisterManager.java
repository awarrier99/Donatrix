package edu.gatech.donatrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.model.User;
import edu.gatech.donatrix.model.UserType;
import edu.gatech.donatrix.model.Manager;

public class testRegisterManager {
    final Database db = Database.getInstance();
    Manager good;
    Manager locked;
    Manager nUser;
    Manager unregistered;
    Manager badUser;

    @Before
    public void setUp() {
        good = new Manager("pawkwah@gmail.com", "pharris36", "Parker Harris");
        locked = new Manager("pawkwah1@gmail.com", "password", "Pete Harris");
        unregistered = new Manager("blah@blah.com", "blah", "Blah");
        nUser = new Manager(null, "null", "Null");
        badUser = new Manager("pawkwah1@gmail.com", "password", "Parker Harris");
        db.registerUser(good);
        db.registerUser(locked);
        db.registerUser(nUser);
    }

    @Test
    public void testUnregisteredManager() {
        assertFalse(db.checkRegisteredUser(unregistered.getEmail(), unregistered.getPassword()));
    }


    @Test
    public void testNullManager() {
        assertFalse(db.checkRegisteredUser(nUser.getEmail(), nUser.getPassword()));
    }

    @Test
    public void testGoodManager() {
        assertTrue(db.checkRegisteredUser(good.getEmail(), good.getPassword()));
    }

    @After
    public void tearDown() {
        db.unregisterUser(good);
        db.unregisterUser(locked);
        db.unregisterUser(nUser);
    }
}