package edu.gatech.donatrix.model;

import java.io.Serializable;

public enum ItemCategory implements Serializable {
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private String category;

    ItemCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
