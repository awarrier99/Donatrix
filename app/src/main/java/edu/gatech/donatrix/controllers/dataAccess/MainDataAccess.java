package edu.gatech.donatrix.controller.dataAccess;

public class MainDataService implements HerokuAPI {

    //These methods are for the most part all implemented, but they all rely on the HerokuAPI
    //interface which is not implemented

    //register a User
    public static void registerUser(User) throws DuplicateUserException, NoNetworkAccessException {
        if (isEmailedRegistered(String email)) {
            throw DuplicateUserException;
        } else {
            addUser(user);
        }
    }

    //check credentials and returns a User on success
    public static User login(String email, String password) throws
            InvalidCredentialsExcpetion,
            LockedAccountException,
            NoNetworkAccessException {
        User user = getUser(String email, String password)
    }

    //potential design flaw. Keep for now. <-- don't delete this comment
    public static Location[] getAllLocations() throws NoNetworkAccessException {
        int[] ids = getAllLocationIds();
        Location[] locations = new Location[ids.length];

        for (int i = 0; i < ids.length; i++) {
            int id = ids[i]
            locations[i] = getLocationById(id);
        }

        return locations;
    }

}