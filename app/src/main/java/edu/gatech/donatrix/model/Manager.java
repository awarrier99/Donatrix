package edu.gatech.donatrix.model;

public class Manager extends User {

    private String name;
    private String email;
    private String password;

    public Manager(String email, String password, String name) {
        super(email, password, name, false, UserType.MANAGER);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
