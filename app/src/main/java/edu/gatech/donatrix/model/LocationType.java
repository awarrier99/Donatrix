package edu.gatech.donatrix.model;

/**
 * An enum for all of our required Location Types
 */
public enum LocationType {
    DROPOFF("DROPOFF"),
    STORE("STORE"),
    WAREHOUSE("WAREHOUSE");

    private final String type;

    /**
     * Will return the string rep of the location type
     * @return A string of the location type.
     */
    public String getType() {
        return this.type;
    }

    LocationType(String type) {
        this.type = type;
    }
}
