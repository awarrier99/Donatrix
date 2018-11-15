package edu.gatech.donatrix.dao;

import android.content.Context;

import java.util.List;

import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.User;

/**
 * A DAO for locations.
 */
public class LocationDao {
    /**
     * A method to get a list of locations.
     * @param context Something.
     * @return The list of locations.
     */
    public static List<Location> getLocations(Context context) {
        return Database.getLocations(context);
    }

    /**
     * A method to get a location by its unique id.
      * @param i The id of the location.
     * @param context Something.
     * @return The location with the id.
     */
    public static Location getLocationByID(Integer i, Context context) {
        return Database.getInstance(context).getLocationByID(i);
    }

    /**
     * A method to add a location employee.
     * @param user The user that is also a location employee
     * @param location The location to add the employee too
     * @param context Something.
     */
    public static void addLocationEmployee(User user, Location location, Context context) {
        Database.getInstance(context).addLocationEmployee(user, location);
    }

    /**
     * A method to get the names of the locations.
     * @param context Something.
     * @return A list of the names of locations.
     */
    public static List<String> getLocationNames(Context context) {
        return Database.getLocationNames(context);
    }
}
