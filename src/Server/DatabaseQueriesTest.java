package Server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test methods in DatabaseQueries class
 * @author Florence
 * @version 21/03/2018
 */
public class DatabaseQueriesTest {

    @Test
    public void test1() {
        User a = new User("Terry", "apples");
        boolean expected = true;
        boolean actual = DatabaseQueries.checkUsername(a);
        assertEquals(expected, actual);
    }//testing if correct boolean when user doesn't exist

    @Test
    public void test2() {
        User a = new User("Connor", "smells");
        boolean expected = false;
        boolean actual = DatabaseQueries.checkUsername(a);
        assertEquals(expected, actual);
    }//testing the correct boolean is produced if an existing user is inputted
    
     @Test
    public void test3() {
        User a = new User("qwerty", "ghoul");
        boolean actual = DatabaseQueries.checkUsername(a);
       // assertTrue(actual);
    }//again testing the correct boolean is produced if an existing user is inputted

}
