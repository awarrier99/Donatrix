package edu.gatech.donatrix.model;

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

    private String category;

    ItemCategory(String category) {
    }

    /**
     * Getter.
     * @return The category of the item.
     */
    public String getCategory() {
        return this.category;
    }
}
