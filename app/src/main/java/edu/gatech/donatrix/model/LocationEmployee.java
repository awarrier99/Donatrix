package edu.gatech.donatrix.model;

import java.sql.Timestamp;

import android.content.Context;

/**
 * A basic location employee
 */
public class LocationEmployee extends User {
    private Location location;

    /**
     * Constructor for a location employee
     * @param email The email of the location employee
     * @param password The password
     * @param name The name of the employee
     * @param location The location to add the employee to.
     */
    public LocationEmployee(String email, String password, String name, Location location) {
        super(email, password, name, false, UserType.LOCATION_EMPLOYEE);
        this.location = location;
    }

    /**
     * Getter
     * @return The location of the employee
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Setter
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * A method to add an item at the location of the employee
     * @param time The time of the donation.
     * @param sDescription The title of the item
     * @param fDescription A more in depth description
     * @param value The value of the item
     * @param category The category (enum)
     * @param comments The comments on the item
     * @param context Something
     */
    public void addItem(Timestamp time, String sDescription, String fDescription,
                        double value, ItemCategory category, String comments, Context context) {
        Item item = new Item(time, this.location, sDescription, fDescription,
                value, category, comments);
        location.addItem(item, context, this);
    }
}