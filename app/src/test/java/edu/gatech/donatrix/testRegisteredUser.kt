package edu.gatech.donatrix

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

import edu.gatech.donatrix.dao.Database
import edu.gatech.donatrix.model.User
import edu.gatech.donatrix.model.UserType

class testRegisteredUser {
    internal val db = Database.instance
    internal var good: User
    internal var locked: User
    internal var nUser: User
    internal var unregistered: User
    internal var badUser: User

    @Before
    fun setUp() {
        good = User("pjharris1998@gmail.com", "pharris36", "Parker Harris", false, UserType.USER)
        locked = User("pjharris1998@gmail.com", "password", "Pete Harris", true, UserType.USER)
        unregistered = User("blah@blah.com", "blah", "Blah", false, UserType.USER)
        nUser = User(null, "null", "Null", false, UserType.USER)
        badUser = User("pjharris1998@gmail.com", "password", "Parker Harris", false, UserType.USER)
        db!!.registerUser(good)
        db!!.registerUser(locked)
        db!!.registerUser(nUser)
    }

    @Test
    fun testUnregisteredUser() {
        assertFalse(db!!.checkRegisteredUser(unregistered.email, unregistered.password))
    }

    @Test
    fun testLockedUser() {
        assertFalse(db!!.checkRegisteredUser(locked.email, locked.password))
    }

    @Test
    fun testNullUser() {
        assertFalse(db!!.checkRegisteredUser(nUser.email, nUser.password))
    }

    @Test
    fun testBadPassword() {
        assertFalse(db!!.checkRegisteredUser(badUser.email, badUser.password))
    }

    @Test
    fun testGoodUser() {
        assertTrue(db!!.checkRegisteredUser(good.email, good.password))
    }

    @After
    fun tearDown() {
        db!!.unregisterUser(good)
        db!!.unregisterUser(locked)
        db!!.unregisterUser(nUser)
    }
}