package edu.gatech.donatrix.model;

/**
 * An admin has the power to _____________.
 */
public class Admin extends User {


    private String name;
    private String email;
    private String password;

    /**
     *
     * @param email unique email address of admin account
     * @param password password of admin account
     * @param name display name of admin account
     */
    public Admin(String email, String password, String name) {
        super(email, password, name, false, UserType.ADMIN);
    }

    /**
     * Gets the current display name
     *
     * @return display name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current email address
     *
     * @return email address of admin account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the current password
     *
     * @return password of admin account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the display name
     *
     * @param name new display name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email address
     *
     * @param email new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password
     *
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
