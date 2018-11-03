package edu.gatech.donatrix.model;

import java.io.Serializable;

public enum UserType implements Serializable {
    ADMIN("ADMIN"),
    LOCATION_EMPLOYEE("LOCATION_EMPLOYEE"),
    MANAGER("MANAGER"),
    USER("USER");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
