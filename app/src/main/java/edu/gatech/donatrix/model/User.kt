package edu.gatech.donatrix.model

import java.io.Serializable

import edu.gatech.donatrix.data.RESTCaller


open class User(email: String, password: String, name: String, locked: Boolean, userType: UserType) : Serializable {

    open var email: String? = null
        internal set
    open var password: String? = null
    open var name: String? = null
    var locked: Boolean = false
    var userType: UserType? = null

    init {
        this.email = email
        this.password = password
        this.name = name
        this.locked = locked
        this.userType = userType
    }

    companion object {

        fun testLoggedIn(body: Map<String, Any>): Boolean {
            val response = RESTCaller.post("https://donatrix-api.herokuapp.com/login", body)
            return response["success"] as Boolean
        }
    }
}