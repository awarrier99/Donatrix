package edu.gatech.donatrix.model;

import java.io.Serializable;

public enum LocationType {
    DROPOFF("DROPOFF"),
    STORE("STORE"),
    WAREHOUSE("WAREHOUSE");

    private final String type;

    public String getType() {
        return this.type;
    }

    LocationType(String type) {
        this.type = type;
    }
}
