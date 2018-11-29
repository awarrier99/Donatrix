package edu.gatech.donatrix.dao

import android.content.Context
import android.provider.ContactsContract
import android.util.Log


import java.util.ArrayList
import java.util.HashMap

import edu.gatech.donatrix.R
import edu.gatech.donatrix.model.Item
import edu.gatech.donatrix.model.Location
import edu.gatech.donatrix.model.LocationEmployee
import edu.gatech.donatrix.model.User
import java.io.*


class Database {

    val locations: List<Location>
        get() = ArrayList(locationMap!!.values)
    val allItems: List<Item>
        get() {
            val items = ArrayList<Item>()
            for (list in itemMap!!.values) {
                items.addAll(list)
            }
            return items
        }

    private constructor(context: Context) {
        load(context)
    }

    private constructor() {

    }

    fun addLocationEmployee(user: User, location: Location) {
        employeeMap!![user as LocationEmployee] = location
    }

    @Throws(Exception::class)
    private fun writeFile(filename: String, map: HashMap<*, *>, context: Context) {
        val fos = context.openFileOutput(filename, Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(map)
        os.close()
        fos.close()
    }

    @Throws(Exception::class)
    private fun readFile(filename: String, flag: String, context: Context) {
        val fis = context.openFileInput(filename)
        val `is` = ObjectInputStream(fis)

        when (flag) {
            USER -> userMap = `is`.readObject() as HashMap<String, User>
            LOC ->
                locationMap = `is`.readObject() as HashMap<Int, Location>
            INVENTORY -> itemMap = `is`.readObject() as HashMap<Location, ArrayList<Item>>
            EMPLOYEE -> employeeMap = `is`.readObject() as HashMap<LocationEmployee, Location>
        }

        `is`.close()
        fis.close()
    }

    private fun save(context: Context) {
        try {
            writeFile("users.db", userMap as HashMap<*,*>, context)
            writeFile("locations.db", locationMap as HashMap<*,*>, context)
            writeFile("items.db", itemMap as HashMap<*,*>, context)
            writeFile("employees.db", employeeMap as HashMap<*,*>, context)
        } catch (e: Exception) {
            Log.d("Donatrix", e.message)
        }

    }

    private fun load(context: Context) {
        try {
            readFile("users.db", USER, context)
            readFile("locations.db", LOC, context)
            readFile("items.db", INVENTORY, context)
            readFile("employees.db", EMPLOYEE, context)
        } catch (e: Exception) {
            Log.d("Donatrix", e.message)
            userMap = HashMap()
            locationMap = HashMap()
            itemMap = HashMap()
            employeeMap = HashMap()
        }

    }

    fun registerUser(user: User, context: Context) {
        if (!userMap!!.containsKey(user.email)) {
            userMap!![user.email!!] = user
            save(context)
        } else {
            throw IllegalArgumentException("Username already taken")
        }
    }

    fun registerUser(user: User) {
        if (userMap != null) {
            if (!userMap!!.containsKey(user.email)) {
                userMap!![user.email!!] = user
            } else {
                throw IllegalArgumentException("Username already taken")
            }
        } else {
            userMap = HashMap()
            userMap!![user.email!!] = user
        }
    }

    fun checkRegisteredUser(username: String?, password: String): Boolean {
        if (userMap != null) {
            if (username != null) {
                val user = userMap!![username]
                return if (user != null) {
                    password == user.password && !user.locked
                } else {
                    false
                }
            } else {
                return false
            }
        }
        return false
    }

    fun loadLocations(context: Context) {
        if (locationMap!!.isEmpty()) {
            try {
                val `is` = context.resources.openRawResource(R.raw.location_data)
                val br = BufferedReader(InputStreamReader(`is`) as Reader?)
                var line: String
                var counter = 0


                for (line in br.lines()) {
                    if (counter > 0) {
                        val info = line.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                        locationMap!![Integer.parseInt(info[0])] = Location(info)
                    }
                    counter++
                }
                save(context)
                br.close()
                `is`.close()
            } catch (e: Exception) {
                Log.d("Zeke", e.message)
            }

        }
    }

    //    public Map<Integer, Location> getLocations() {
    //        return locationMap;
    //    }
    fun addItem(item: Item, employee: LocationEmployee) {
        var items: MutableList<Item>? = itemMap!![employee.location]
        if (items == null) {
            items = ArrayList()
        }
        items.add(item)
        itemMap!![employee.location!!] = (items as ArrayList<Item>?)!!
    }

    fun getUser(username: String): User? {
        return userMap!![username]
    }

    fun getLocation(employee: LocationEmployee): Location? {
        return employeeMap!![employee]
    }

    fun getLocationByID(i: Int?): Location? {
        return locationMap!!.get(i)
    }

    fun getItemsFromLocation(location: Location): List<Item>? {
        return itemMap!![location]
    }

    fun unregisterUser(user: User) {
        if (userMap != null) {
            userMap!!.remove(user.email)
        }
    }

    companion object {
        private var ourInstance: Database? = null
        private var userMap: MutableMap<String, User>? = null
        private var locationMap: MutableMap<Int, Location>? = null
        private var itemMap: MutableMap<Location, ArrayList<Item>>? = null
        private var employeeMap: MutableMap<LocationEmployee, Location>? = null

        private val USER = "USERS"
        private val LOC = "LOCATION"
        private val INVENTORY = "INVENTORY"
        private val EMPLOYEE = "EMPLOYEE"

        fun getInstance(context: Context): Database? {
            return if (Database.ourInstance != null) {
                ourInstance
            } else
                Database(context)
        }

        val instance: Database?
            get() = if (Database.ourInstance != null) {
                ourInstance
            } else Database()
    }
}
