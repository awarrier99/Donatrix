package edu.gatech.donatrix.model

import java.io.Serializable

enum class UserType private constructor(val type: String) {
    USER("USER"),
    LOCATION_EMPLOYEE("LOCATION_EMPLOYEE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN")

}
