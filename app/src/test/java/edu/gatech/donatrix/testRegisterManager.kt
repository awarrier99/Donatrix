package edu.gatech.donatrix

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

import edu.gatech.donatrix.dao.Database
import edu.gatech.donatrix.model.User
import edu.gatech.donatrix.model.UserType
import edu.gatech.donatrix.model.Manager

class testRegisterManager {
    internal val db = Database.instance
    internal var good: Manager
    internal var locked: Manager
    internal var nUser: Manager
    internal var unregistered: Manager
    internal var badUser: Manager

    @Before
    fun setUp() {
        good = Manager("pawkwah@gmail.com", "pharris36", "Parker Harris")
        locked = Manager("pawkwah1@gmail.com", "password", "Pete Harris")
        unregistered = Manager("blah@blah.com", "blah", "Blah")
        nUser = Manager(null, "null", "Null")
        badUser = Manager("pawkwah1@gmail.com", "password", "Parker Harris")
        db!!.registerUser(good)
        db!!.registerUser(locked)
        db!!.registerUser(nUser)
    }

    @Test
    fun testUnregisteredManager() {
        assertFalse(db!!.checkRegisteredUser(unregistered.email, unregistered.password))
    }


    @Test
    fun testNullManager() {
        assertFalse(db!!.checkRegisteredUser(nUser.email, nUser.password))
    }

    @Test
    fun testGoodManager() {
        assertTrue(db!!.checkRegisteredUser(good.email, good.password))
    }

    @After
    fun tearDown() {
        db!!.unregisterUser(good)
        db!!.unregisterUser(locked)
        db!!.unregisterUser(nUser)
    }
}