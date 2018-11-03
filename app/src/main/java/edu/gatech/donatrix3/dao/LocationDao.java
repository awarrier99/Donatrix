package edu.gatech.donatrix3.dao;

import android.content.Context;

import java.util.List;
import java.util.HashMap;
import edu.gatech.donatrix3.model.Location;
import edu.gatech.donatrix3.model.User;

//public class LocationDao {
//    public static List<Location> getLocations(Context context) {

public class LocationDao {
    public static List<Location> getLocations(Context context) {
        return Database.getInstance(context).getLocations();
    }
    public static Location getLocationByID(Integer i, Context context) {
        return Database.getInstance(context).getLocationByID(i);
    }
    public static void addLocationEmployee(User user, Location location, Context context) {
        Database.getInstance(context).addLocationEmployee(user, location);
    }
}
