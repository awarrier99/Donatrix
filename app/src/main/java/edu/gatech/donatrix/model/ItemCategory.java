package edu.gatech.donatrix.model;

import java.io.Serializable;

// Paul: Use toString() instead of giving this enum a param

/**
 * The enum for the Categories we must have.
 */
public enum ItemCategory {
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private final String category;

    ItemCategory(String category) {
        this.category = category;
    }

    /**
     * Getter.
     * @return The category of the item.
     */
    public String getCategory() {
        return this.category;
    }
}
