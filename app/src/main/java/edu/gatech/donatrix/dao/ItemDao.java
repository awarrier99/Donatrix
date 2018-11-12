package edu.gatech.donatrix.dao;


import android.content.Context;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import edu.gatech.donatrix.model.Item;
import edu.gatech.donatrix.model.ItemCategory;
import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.LocationEmployee;

/**
 * Dao for manipulating items.
 */
public class ItemDao {
    /**
     * A method to add items to the Singleton
     * @param employee the person registering the item
     * @param sDescription The title.
     * @param fDescription The fuller description.
     * @param value The price.
     * @param category The category.
     * @param comments The comments.
     * @param context The context??? IDK.
     */
    public static void addItem(LocationEmployee employee, String sDescription, String fDescription,
                               double value, ItemCategory category, String comments,
                               Context context) {
        Date date = Date.from(Instant.now());
        Timestamp timestamp = new Timestamp(date.getTime());
        Item item = new Item(timestamp, employee.getLocation(), sDescription, fDescription,
                value, category, comments);
        Database.getInstance(context).addItem(item, employee);
    }

    /**
     * Get all the Items from the specific Location
     * @param location The location to get items from.
     * @param context I still don't know.
     * @return A list of Items from the Location.
     */
    public static List<Item> getItemsFromLocation(Location location, Context context) {
        return Database.getInstance(context).getItemsFromLocation(location);
    }

    /**
     * A method to get All Items
     * @param context Still of no help. Something for Singleton.
     * @return The List of all the Items.
     */
    public static List<Item> getAllItems(Context context) {
        return Database.getInstance(context).getAllItems();
    }
}