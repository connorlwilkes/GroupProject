package Server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginUserTest {

    @Test
    public void test1() {
        User b = new User("Sophia", "wow");
        ServerProtocol a = new ServerProtocol("true", "Successfully logged in");
        String expected = a.toString();
        String actual = LoginUser.CheckLogin(b).toString();
        assertEquals(expected, actual);
    }//checking whether you can login with correct credentials

    @Test
    public void test2() {
        User b = new User("Sally", "xxxx");
        ServerProtocol a = new ServerProtocol("false", "Failure");
        String expected = a.toString();
        String actual = LoginUser.CheckLogin(b).toString();
        assertEquals(expected, actual);
    }//checking whether you can log in with an existing username and incorrect password

    @Test
    public void test3() {
        User b = new User("Sarah", "xxxx");
        ServerProtocol a = new ServerProtocol("false", "User does not exist");
        String expected = a.toString();
        String actual = LoginUser.CheckLogin(b).toString();
        assertEquals(expected, actual);
    }//check whether you can log in with a non existing username and password
}


