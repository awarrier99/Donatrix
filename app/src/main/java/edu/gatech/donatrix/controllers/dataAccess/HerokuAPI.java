package edu.gatech.donatrix.controller.dataAccess;

public class HerokuAPI {

    //returns true if provided email is registered in the database
    //May be too much logic for this interface but keep it this way for now
    public static boolean isEmailRegistered(String email) throws NoNetworkAccessException{

    }

    //Unconditionally adds a user to the database
    public static boolean addUser(User user) throws NoNetworkAccessExcpetion {

    }

    //Gets a user
    public static User getUser(String email, String password) throws
            NoNetworkAccessException,
            InvalidCredentialsException,
            LockedAccountException {

    }

    //Gets location data by id
    public static Location getLocationById(int id) throws
            NoNetworkAccessException, InvalidLocationException {

    }

    //Returns the ids of all locations <-- Potential design flaw (Don't delete comment)
    public static int[] getAllLocationIds() throws NoNetworkAccessException {

    }
}