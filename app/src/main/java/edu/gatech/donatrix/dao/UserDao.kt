package edu.gatech.donatrix.dao

import android.content.Context

import edu.gatech.donatrix.model.User


object UserDao {
    @Throws(IllegalArgumentException::class)
    fun registerUser(user: User, context: Context) {
        Database.getInstance(context)!!.registerUser(user, context)
    }

    fun checkRegisteredUser(email: String, password: String, context: Context): Boolean {
        return Database.getInstance(context)!!.checkRegisteredUser(email, password)
    }

    fun getUser(email: String, context: Context): User? {
        return Database.getInstance(context)!!.getUser(email)
    }
}
