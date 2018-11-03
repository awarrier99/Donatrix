package edu.gatech.donatrix.model;


public class Admin extends User {


    private String name;
    private String email;
    private String password;

    public Admin(String email, String password, String name) {
        super(email, password, name, false, UserType.ADMIN);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
