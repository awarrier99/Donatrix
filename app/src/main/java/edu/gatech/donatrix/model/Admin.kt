package edu.gatech.donatrix.model

/**
 * An admin has the power to _____________.
 */
class Admin
/**
 *
 * @param email unique email address of admin account
 * @param password password of admin account
 * @param name display name of admin account
 */
(email: String, password: String, name: String) : User(email, password, name, false, UserType.ADMIN) {


    /**
     * Gets the current display name
     *
     * @return display name
     */
    /**
     * Sets the display name
     *
     * @param name new display name
     */
    override var name: String? = null
    /**
     * Gets the current email address
     *
     * @return email address of admin account
     */
    /**
     * Sets the email address
     *
     * @param email new email address
     */
    override var email: String? = null
    /**
     * Gets the current password
     *
     * @return password of admin account
     */
    /**
     * Sets the password
     *
     * @param password new password
     */
    override var password: String? = null
}
