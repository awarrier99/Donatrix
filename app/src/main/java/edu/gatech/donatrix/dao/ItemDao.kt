package edu.gatech.donatrix.dao


import android.content.Context

import java.sql.Timestamp
import java.time.Instant
import java.util.Date

import edu.gatech.donatrix.model.Item
import edu.gatech.donatrix.model.ItemCategory
import edu.gatech.donatrix.model.Location
import edu.gatech.donatrix.model.LocationEmployee

object ItemDao {

    fun addItem(employee: LocationEmployee, sDescription: String, fDescription: String,
                value: Double, category: ItemCategory, comments: String, context: Context) {
        val date = Date.from(Instant.now())
        val timestamp = Timestamp(date.time)
        val item = Item(timestamp, employee.location, sDescription, fDescription,
                value, category, comments)
        Database.getInstance(context)!!.addItem(item, employee)
    }

    fun getItemsFromLocation(location: Location, context: Context): List<Item>? {
        return Database.getInstance(context)!!.getItemsFromLocation(location)
    }

    fun getAllItems(context: Context): List<Item> {
        return Database.getInstance(context)!!.allItems
    }
}