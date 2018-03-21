package Server;

import java.util.Objects;

/**
 * User class for the minigame game. Has the fields username and password of type String and thread of type ServerThread
 *
 * @author Florence
 * @version 21/03/2018
 */
public class User {

    private String username;
    private String password;
    private ServerThread thread;

    /**
     * Constructor for the user class, reuses the field variables user and password as its parameters
     *
     * @param username The username of the User
     * @param password The password of the User
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter for the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username
     *
     * @param username The new username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password
     *
     * @param password The new password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to verify the password of a user
     *
     * @param password Password to check
     * @return true if password matches, false otherwise
     */
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return username;
    }

    /**
     * Equals method for the user class comparing username and password equality
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

}
