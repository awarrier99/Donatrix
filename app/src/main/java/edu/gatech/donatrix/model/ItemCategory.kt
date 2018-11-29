package edu.gatech.donatrix.model

import java.io.Serializable

// Paul: Use toString() instead of giving this enum a param

enum class ItemCategory private constructor(val category: String) {
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other")
}
