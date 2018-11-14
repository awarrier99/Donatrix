package edu.gatech.donatrix.model;

/**
 * type of user
 */
public enum UserType {
    USER("USER"),
    LOCATION_EMPLOYEE("LOCATION_EMPLOYEE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private final String type;

    /**
     * constructor
     * @param type type of user
     */
    public UserType(String type) {
        this.type = type;
    }

    /**
     * type getter
     * @return type
     */
    public String getType() {
        return this.type;
    }

}
