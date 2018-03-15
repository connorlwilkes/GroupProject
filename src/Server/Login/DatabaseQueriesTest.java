package Server.Login;

import org.junit.Test;

import static org.junit.Assert.*;
import Server.User;

public class DatabaseQueriesTest {

    @Test
    public void test1(){
         User a = new User("Terry", "apples");
            boolean expected = false;
            boolean actual = Server.Login.DatabaseQueries.checkUsername(a);
            assertEquals(expected,actual);
    }


}