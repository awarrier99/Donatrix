package edu.gatech.donatrix.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.User;

public class LocationDao {
    public static List<Location> getLocations(Context context) {
        if (Database.getInstance(context).getLocations().isEmpty()) {
            Database.getInstance(context).loadLocations(context);
        }
        return Database.getInstance(context).getLocations();
    }
    public static Location getLocationByID(Integer i, Context context) {
        return Database.getInstance(context).getLocationByID(i);
    }
    public static void addLocationEmployee(User user, Location location, Context context) {
        Database.getInstance(context).addLocationEmployee(user, location);
    }
    public static List<String> getLocationNames(Context context) {
        if (Database.getInstance(context).getLocations().isEmpty()) {
            Database.getInstance(context).loadLocations(context);
        }
        List<Location> locList = Database.getInstance(context).getLocations();
        List<String> strList = new ArrayList<>();
        for (Location loc: locList) {
            strList.add(loc.getName());
        }
        return strList;
    }
}
