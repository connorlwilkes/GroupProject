package Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterUserTest {

    @Test
            public void test1(){
        User a = new User ("Connor","smells");
        ServerProtocol b = new ServerProtocol("true","Successfully registered user");
        String expected = b.toString() ;
        String actual = RegisterUser.checkUser(a).toString();
        assertEquals(expected,actual);
    }//testing if you can register a new user
    @Test
        public void test2(){
        User b = new User("Sally", "coach3");
        ServerProtocol a = new ServerProtocol("false","User already exists");
        String expected = a.toString();
        String actual = RegisterUser.checkUser(b).toString();
        assertEquals(expected,actual);
    }//testing if the correct server protocol is thrown if the username already exists


}