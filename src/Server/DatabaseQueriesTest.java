package Server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseQueriesTest {

    @Test
    public void test1() {
        User a = new User("Terry", "apples");
        boolean expected = true;
        boolean actual = DatabaseQueries.checkUsername(a);
        assertEquals(expected, actual);
    }//testing if correct boolean when user doesnt exist

    @Test
    public void test2() {
        User a = new User("Connor", "smells");
        boolean expected = false;
        boolean actual = DatabaseQueries.checkUsername(a);
        assertEquals(expected, actual);
    }

}