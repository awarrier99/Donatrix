package edu.gatech.donatrix.dao;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.model.Item;
import edu.gatech.donatrix.model.Location;
import edu.gatech.donatrix.model.LocationEmployee;
import edu.gatech.donatrix.model.User;


@SuppressWarnings("ALL")
/**
 * The Database controller
 */
public class Database {
    private static Database ourInstance;
    private static Map<String, User> userMap;
    private static Map<Integer, Location> locationMap;
    private static Map<Location, ArrayList<Item>> itemMap;
    private static Map<LocationEmployee, Location> employeeMap;

    private static final String USER = "USERS";
    private static final String LOC = "LOCATION";
    private static final String INVENTORY = "INVENTORY";
    private static final String EMPLOYEE = "EMPLOYEE";

    /**
     * Returns an instance of the database
     *
     * @param context the current context
     * @return the instance
     */
    public static Database getInstance(Context context) {
        if (Database.ourInstance != null) {
            return ourInstance;
        }
        return ourInstance = new Database(context);
    }

    /**
     * Returns an instance of the database
     *
     * @return the instance
     */
    public static Database getInstance() {
        if (Database.ourInstance != null) {
            return ourInstance;
        }
        return ourInstance = new Database();
    }

    private Database(Context context) {
        load(context);
    }

    private Database() {

    }

    /**
     * Adds  a location employee
     *
     * @param user the user to add
     * @param location the location of the user
     */
    public void addLocationEmployee(User user, Location location) {
        employeeMap.put((LocationEmployee) user, location);
    }

    private void writeFile(String filename, HashMap map, Context context) throws Exception {
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(map);
        os.close();
        fos.close();
    }

    private void readFile(String filename, String flag, Context context) throws Exception {
        FileInputStream fis = context.openFileInput(filename);
        ObjectInputStream is = new ObjectInputStream(fis);

        switch (flag) {
            case USER:
                userMap = (HashMap<String, User>) is.readObject();
                break;
            case LOC:
                //noinspection unchecked
                locationMap = (HashMap<Integer, Location>) is.readObject();
                break;
            case INVENTORY:
                itemMap = (HashMap<Location, ArrayList<Item>> ) is.readObject();
                break;
            case EMPLOYEE:
                employeeMap = (HashMap<LocationEmployee, Location>) is.readObject();
                break;
        }

        is.close();
        fis.close();
    }

    private void save(Context context) {
        try {
            writeFile("users.db", (HashMap<String, User>) userMap, context);
            writeFile("locations.db", (HashMap<Integer, Location>) locationMap, context);
            writeFile("items.db", (HashMap<Location, ArrayList<Item>>) itemMap, context);
            writeFile("employees.db", (HashMap<LocationEmployee, Location>) employeeMap, context);
        } catch (Exception e) {
            Log.d("Donatrix", e.getMessage());
        }
    }

    private void load(Context context) {
        try {
            readFile("users.db", USER, context);
            readFile("locations.db", LOC, context);
            readFile("items.db", INVENTORY, context);
            readFile("employees.db", EMPLOYEE, context);
        } catch (Exception e) {
            Log.d("Donatrix", e.getMessage());
            userMap = new HashMap<>();
            locationMap = new HashMap<>();
            itemMap = new HashMap<>();
            employeeMap = new HashMap<>();
        }
    }

    /**
     * Adds a user to the database
     *
     * @param user the user to add
     * @param context the current context
     */
    public void registerUser(User user, Context context) {
        if (!userMap.containsKey(user.getEmail())) {
            userMap.put(user.getEmail(), user);
            save(context);
        } else {
            throw new IllegalArgumentException("Username already taken");
        }
    }

    /**
     * Adds a user to the database
     *
     * @param user the user to add
     */
    public void registerUser(User user) {
        if (userMap != null) {
            if (!userMap.containsKey(user.getEmail())) {
                userMap.put(user.getEmail(), user);
            } else {
                throw new IllegalArgumentException("Username already taken");
            }
        } else {
            userMap = new HashMap<>();
            userMap.put(user.getEmail(), user);
        }
    }

    /**
     * Confirms if a user is registered
     *
     * @param username username of the user
     * @param password password of the usser
     * @return true if the user is registered
     */
    public boolean checkRegisteredUser(String username, String password) {
        if (userMap != null) {
            if (username != null) {
                User user = userMap.get(username);
                if (user != null) {
                    return password.equals(user.getPassword()) && !user.getLocked();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Loads all locations
     *
     * @param context the current context
     */
    public void loadLocations(Context context) {
        if (locationMap.isEmpty()) {
            try {
                InputStream is = context.getResources().openRawResource(R.raw.location_data);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                int counter = 0;


                while ((line = br.readLine()) != null) {
                    if (counter > 0) {
                        String[] info = line.split(",");
                        locationMap.put(Integer.parseInt(info[0]), new Location(info));
                    }
                    counter++;
                }
                save(context);
                br.close();
                is.close();
            } catch (Exception e) {
                Log.d("Zeke", e.getMessage());
            }
        }
    }

    /**
     * Gets all of the locations
     *
     * @return list of locations
     */
    public List<Location> getLocations() {
        return new ArrayList<>(locationMap.values());
    }

    //    public Map<Integer, Location> getLocations() {
//        return locationMap;
//    }

    /**
     * Adds an item
     *
     * @param item item to add
     * @param employee the employee adding the item
     */
    public void addItem(Item item, LocationEmployee employee) {
        List<Item> items = itemMap.get(employee.getLocation());
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        itemMap.put(employee.getLocation(), (ArrayList<Item>) items);
    }
    public User getUser(String username) {
        return userMap.get(username);
    }
    public Location getLocation(LocationEmployee employee) {
        return employeeMap.get(employee);
    }
    public Location getLocationByID(Integer i) {
        return locationMap.get(i);
    }
    public List<Item> getItemsFromLocation(Location location) {
        return itemMap.get(location);
    }
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for (List<Item> list : itemMap.values()) {
            items.addAll(list);
        }
        return items;
    }

    /**
     * removes a user from the database
     *
     * @param user the user to remove
     */
    public void unregisterUser(User user) {
        if (userMap != null) {
            userMap.remove(user.getEmail());
        }
    }
    public static List<Location> getLocations(Context context) {
        Database db = Database.getInstance(context);
        if (db.getLocations().isEmpty()) {
            db.loadLocations(context);
        }
        return db.getLocations();
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
