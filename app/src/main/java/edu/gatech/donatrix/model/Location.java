package edu.gatech.donatrix.model;

import java.io.Serializable;
import edu.gatech.donatrix.dao.Database;
import android.content.Context;


public class Location implements Serializable {
    private int key;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private LocationType locationType;
    private String number;
    private String website;
    private ItemManager inventory;

    public Location(String name) {
        this.name = name;
    }

    public Location(String[] info) {
        this.key = Integer.parseInt(info[0]);
        this.setName(info[1]);
        this.setLatitude(info[2]);
        this.setLongitude(info[3]);
        this.setAddress(info[4]);
        this.setCity(info[5]);
        this.setState(info[6]);
        this.setZip(info[7]);
        this.setLocationType(LocationType.valueOf(info[8].replace(" ", "").toUpperCase()));
        this.setNumber(info[9]);
        this.setWebsite(info[10]);
    }

    public int getKey() {
        return key;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    private void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    private void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    private void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    private void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    private void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    private void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void addItem(Item item, Context context, LocationEmployee employee) {
        Database.getInstance(context).addItem(item, employee);
    }

    public void removeItem(Item item) {
        this.inventory.removeItem(item);
    }
    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s | %s | %s | %s | %s | %s", name, latitude, longitude, address, city, state, zip, locationType.getType(), number, website);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        } else {
            return this.key == ((Location) obj).key && this.address.equals(((Location) obj).address) && this.latitude.equals(((Location) obj).latitude) && this.city.equals(((Location) obj).city) && this.locationType == ((Location) obj).locationType && this.name.equals(((Location) obj).name) && this.longitude.equals(((Location) obj).longitude) && this.number.equals(((Location) obj).number) && this.state.equals(((Location) obj).state) && this.website.equals(((Location) obj).website) && this.zip.equals(((Location) obj).zip);
        }
    }

    @Override
    public int hashCode() {
        return this.key * this.name.length();
    }
}
