package edu.gatech.donatrix.dao

import android.content.Context

import java.util.ArrayList

import edu.gatech.donatrix.model.Location
import edu.gatech.donatrix.model.User

object LocationDao {
    fun getLocations(context: Context): List<Location> {
        if (Database.getInstance(context)!!.locations.isEmpty()) {
            Database.getInstance(context)!!.loadLocations(context)
        }
        return Database.getInstance(context)!!.locations
    }

    fun getLocationByID(i: Int?, context: Context): Location? {
        return Database.getInstance(context)!!.getLocationByID(i)
    }

    fun addLocationEmployee(user: User, location: Location, context: Context) {
        Database.getInstance(context)!!.addLocationEmployee(user, location)
    }

    fun getLocationNames(context: Context): List<String?> {
        if (Database.getInstance(context)!!.locations.isEmpty()) {
            Database.getInstance(context)!!.loadLocations(context)
        }
        val locList = Database.getInstance(context)!!.locations
        val strList = ArrayList<String?>()
        for (loc in locList) {
            strList.add(loc.name)
        }
        return strList
    }
}
