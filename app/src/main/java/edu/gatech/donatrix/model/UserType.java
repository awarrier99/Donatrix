package edu.gatech.donatrix.model;

import java.io.Serializable;

public enum UserType {
    USER("USER"),
    LOCATION_EMPLOYEE("LOCATION_EMPLOYEE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
