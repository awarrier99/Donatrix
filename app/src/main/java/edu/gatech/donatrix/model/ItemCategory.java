package edu.gatech.donatrix.model;

import java.io.Serializable;

// Paul: Use toString() instead of giving this enum a param

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

    public String getCategory() {
        return this.category;
    }
}
