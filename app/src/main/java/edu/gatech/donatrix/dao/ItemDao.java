package edu.gatech.donatrix.dao;


import android.content.Context;
import android.util.Log;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import edu.gatech.donatrix.model.Item;
import edu.gatech.donatrix.model.ItemCategory;
import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.LocationEmployee;

class ItemDao {

    public static void addItem(LocationEmployee employee, String sDescription, String fDescription,
                               double value, ItemCategory category, String comments, Context context) {
        Date date = Date.from(Instant.now());
        Timestamp timestamp = new Timestamp(date.getTime());
        Log.d("Zeke3", employee.getLocation().toString());
        Item item = new Item(timestamp, employee.getLocation(), sDescription, fDescription,
                value, category, comments);
        Log.d("Zeke5", item.getsDescription());
        Database.getInstance(context).addItem(item, employee);
    }
    public static List<Item> getItemsFromLocation(Location location, Context context) {
        Log.d("Zeke1", "Here");
        return Database.getInstance(context).getItemsFromLocation(location);
    }
    public static List<Item> getAllItems(Context context) {
        return Database.getInstance(context).getAllItems();
    }
}