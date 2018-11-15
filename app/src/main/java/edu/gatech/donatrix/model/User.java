package edu.gatech.donatrix.model;

import java.io.Serializable;
import java.util.Map;

import edu.gatech.donatrix.data.RESTCaller;

/**
 * A basic User class. Superclass of Admin, Manager, and Employee
 */
public class User implements Serializable {

    private String email;
    private String password;
    private String name;
    private boolean locked;
    private UserType userType;

    /**
     * User constructor
     *
     * @param email user email
     * @param password user password
     * @param name user name
     * @param locked user locked state
     * @param userType type of user
     */
    public User(String email, String password, String name, boolean locked, UserType userType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.locked = locked;
        this.userType = userType;
    }

    /**
     * email setter
     * @param email email
     */
    void setEmail(String email) {
        this.email = email;
    }

    /**
     * email getter
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * password setter
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * password getter
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * name setter
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * name getter
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * loced setter
     * @param locked locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * locked getter
     * @return locked
     */
    public boolean getLocked() {
        return this.locked;
    }

    /**
     * user type setter
     * @param userType user type
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * user type getter
     * @return user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Log in tester
     * @param body body to test
     * @return true if successful
     */
    public static boolean testLoggedIn(Map<String, Object> body) {
        Map<String, Object> response = RESTCaller.post(
                "https://donatrix-api.herokuapp.com/login", body);
        return (boolean) response.get("success");
    }
}