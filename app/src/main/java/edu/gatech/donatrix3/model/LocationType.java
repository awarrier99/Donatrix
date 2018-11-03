package edu.gatech.donatrix3.model;

import java.io.Serializable;

public enum LocationType implements Serializable {
    DROPOFF("DROPOFF"),
    STORE("STORE"),
    WAREHOUSE("WAREHOUSE");

    private String type;

    public String getType() {
        return this.type;
    }

    LocationType(String type) {
        this.type = type;
    }
}
