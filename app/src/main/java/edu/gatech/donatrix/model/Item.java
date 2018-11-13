package edu.gatech.donatrix.model;

import java.sql.Timestamp;

import java.io.Serializable;

/**
 * An item class
 */
public class Item implements Serializable {

    private Timestamp time;
    private Location location;
    private String sDescription;
    private String fDescription;
    private double value;
    private ItemCategory category;
    private String comments;

    /**
     * A constructor of items
     * @param time Time donated
     * @param location Location donated.
     * @param sDescription Title
     * @param fDescription Bigger description
     * @param value Price.
     * @param category Is it clothing, or nah.
     * @param comments Anything else to add?
     */
    public Item(Timestamp time, Location location, String sDescription, String fDescription,
                double value, ItemCategory category, String comments) {
        this.time = time;
        this.location = location;
        this.sDescription = sDescription;
        this.fDescription = fDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }

    /**
     * Getter
     * @return The time.
     */
    public Timestamp getTime() {
        return (java.sql.Timestamp) time.clone();
    }

    /**
     * Setter.
     * @param time I believe the time is...
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * Getter
     * @return The relativistic position.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gimme that name
     * @return That name.
     */
    public String getsDescription() {
        return sDescription;
    }

    /**
     * Set that name
     * @param sDescription to something informative.
     */
    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    /**
     * I want to know more of this item
     * @return Therefore, this method provides that information.
     */
    public String getfDescription() {
        return fDescription;
    }

    /**
     * Setter
     * @param fDescription of the full description
     */
    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    /**
     * How much you want for dis?
     * @return How in-debt I will be.
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter.
     * @param value Set the market price.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Getter.
     * @return The category of the item.
     */
    public ItemCategory getCategory() {
        return category;
    }

    /**
     * Setter.
     * @param category the category of this item.
     */
    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    /**
     * Getter.
     * @return The comments of the item.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Setter
     * @param comments The comments on this item.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

//    @Override
//    public boolean equals(@androidx.annotation.Nullable Object obj) {
//        return super.equals(obj);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
}