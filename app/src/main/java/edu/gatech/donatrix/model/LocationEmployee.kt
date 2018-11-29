package edu.gatech.donatrix.model

import java.sql.Timestamp

import android.content.Context


class LocationEmployee(email: String, password: String, name: String,
        //    public LocationEmployee(Map<String, Object> map) {
        //        super((String) map.get("email"), (String) map.get("password"), (String) map.get("name"), (int) map.get("locked") != 0, UserType.valueOf((String) map.get("type")));
        //
        //    }

                       var location: Location?) : User(email, password, name, false, UserType.LOCATION_EMPLOYEE) {

    fun addItem(time: Timestamp, sDescription: String, fDescription: String,
                value: Double, category: ItemCategory, comments: String, context: Context) {
        val item = Item(time, this.location, sDescription, fDescription,
                value, category, comments)
        location!!.addItem(item, context, this)
    }
}