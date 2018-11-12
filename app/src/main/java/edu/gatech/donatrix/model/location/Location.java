package edu.gatech.donatrix.model.location;

public class Location {
    private int id;
    private String name;
    private Coordinate coordinate;
    private Address address;
    private LocationType locationType;
    private String phone;
    private String website;
    private ArrayList inventory; //May extract this to an Inventory Oobject later. Keep for now

    //getters and setters
}