package Server.Login;
import Server.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterUserTest {

    @Test
            public void test1(){
        User a = new User ("Sally","apple32");
        int expected = 0;
        int actual = Server.Login.RegisterUser.checkUser(a);
        assertEquals(expected,actual);
    }



}