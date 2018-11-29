package edu.gatech.donatrix.model

import java.io.Serializable

enum class LocationType private constructor(val type: String) {
    DROPOFF("DROPOFF"),
    STORE("STORE"),
    WAREHOUSE("WAREHOUSE")
}
