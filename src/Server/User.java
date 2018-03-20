package Server;

import java.util.Objects;

/**
 * User class for the minigame game.
 */
public class User {

    private String username;
    private String password;
    private ServerThread thread;

    /**
     * Constructor for the user class
     *
     * @param username
     * @param password
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
     * @param username username to set to
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
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verifies the password of a user
     *
     * @param password password to check
     * @return true if password matches, false otherwise
     */
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
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
