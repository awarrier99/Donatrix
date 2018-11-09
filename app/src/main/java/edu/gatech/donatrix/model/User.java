package edu.gatech.donatrix.model;

import java.io.Serializable;
import java.util.Map;

import edu.gatech.donatrix.data.RESTCaller;


public class User implements Serializable {

    private String email;
    private String password;
    private String name;
    private boolean locked;
    private UserType userType;

    User(String email, String password, String name, boolean locked, UserType userType) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setLocked(locked);
        this.setUserType(userType);
    }

    void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public boolean getLocked() {
        return this.locked;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public UserType getUserType() {
        return userType;
    }

    public static boolean testLoggedIn(Map<String, Object> body) {
        System.out.println(body);
        Map<String, Object> response = RESTCaller.post("https://donatrix-api.herokuapp.com/login", body);
        System.out.println(response.get("success"));
        return (boolean) response.get("success");
    }
}
