package edu.gatech.donatrix.model;

/**
 * A basic Manager class
 */
class Manager extends User {

    private String name;
    private String email;
    private String password;

    /**
     * Manager constructor
     *
     * @param email manager email
     * @param password manager password
     * @param name manager name
     */
    public Manager(String email, String password, String name) {
        super(email, password, name, false, UserType.MANAGER);
    }

    /**
     * name getter
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * email getter
     * @return email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * password getter
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * name setter
     * @param name The name to set the Manager to
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * email setter
     * @param email the email to set
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * password setter
     * @param password the password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
