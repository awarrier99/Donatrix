package edu.gatech.donatrix3.dao;


import android.content.Context;
import java.sql.Timestamp;
import java.util.Date;
import java.time.Instant;
import java.util.List;

public class ItemDao {

    public static void addItem(LocationEmployee employee, String sDescription, String fDescription,
                               double value, ItemCategory category, String comments, Context context) {
        Date date = Date.from(Instant.now());
        Timestamp timestamp = new Timestamp(date.getTime());
        Item item = new Item(timestamp, employee.getLocation(), sDescription, fDescription,
                value, category, comments);
        Database.getInstance(context).addItem(item, employee);
    }
    public static List<Item> getItemsFromLocation(Location location, Context context) {
        return Database.getInstance(context).getItemsFromLocation(location);
    }
    public static List<Item> getAllItems(Context context) {
        return Database.getInstance(context).getAllItems();
    }
}