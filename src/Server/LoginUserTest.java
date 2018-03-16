package Server;

import static org.junit.Assert.*;

import Server.User;


import org.junit.Test;

public class LoginUserTest {

    @Test
    public void test1() {
        User b = new User("Sally", "coach3");
        ServerProtocol a = new ServerProtocol("true", "Successfully logged in");
        String expected = a.toString();
        String actual = LoginUser.CheckLogin(b).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        User b = new User("Sally", "xxxx");
        ServerProtocol a = new ServerProtocol("false", "Failure");
        String expected = a.toString();
        String actual = LoginUser.CheckLogin(b).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        User b = new User("Sarah", "xxxx");
        ServerProtocol a = new ServerProtocol("false", "User does not exist");
        String expected = a.toString();
        String actual = LoginUser.CheckLogin(b).toString();
        assertEquals(expected, actual);
    }
}


