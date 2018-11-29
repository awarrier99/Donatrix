package edu.gatech.donatrix.model

class Manager(email: String, password: String, name: String) : User(email, password, name, false, UserType.MANAGER) {

    override var name: String? = null
    override var email: String? = null
    override var password: String? = null
}
