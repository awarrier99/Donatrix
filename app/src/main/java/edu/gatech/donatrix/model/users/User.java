package edu.gatech.donatrix.model.users;


public class User extends Serializable {
    private String name;
    private String email;
    private String password;
    private String isLocked;

    /**
     *
     * @param name the name of the user
     * @param email the unique email of the user
     * @param password the password of the user
     * @param isLocked if true, the user account is in a disabled state
     */
    public User(String name, String email, String password, String isLocked) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLocked = isLocked;
    }

    //TODO: Write getters and setters for the above params WITH JAVA DOCS!!

    //Two users are equal if and only if their emails are the same
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;

        return o.email.equals(this.email);
    }

    @Override
    public int hashcode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + isLocked ? 1 : 0;
        return result;
    }
}