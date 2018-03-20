package Server;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;

public class RegisterUserTest {

    @Test
    public void test1() {
        User a = new User("Sophia", "wow");
        ServerProtocol b = new ServerProtocol("true", "Successfully registered user");
        String expected = b.toString();
        String actual = null;
        try {
            actual = RegisterUser.checkUser(a).toString();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }//testing if you can register a new user

    @Test
    public void test2() {
        User b = new User("Sally", "coach3");
        ServerProtocol a = new ServerProtocol("false", "User already exists");
        String expected = a.toString();
        String actual = null;
        try {
            actual = RegisterUser.checkUser(b).toString();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }//testing if the correct server protocol is thrown if the username already exists


}