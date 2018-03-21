package Server;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class to test methods in User class
 * @author Florence
 * @version 21/03/2018
 */
public class UserTest {
	
	
	@Test
	public void testPassword(){
		User u1 = new User("lxp","password1" );
		
		boolean ans = true;
		boolean val;
		
		val = u1.verifyPassword("password1");
		
		assertEquals(ans, val);
	}
	
	@Test
	public void testPassword2(){
		User u1 = new User("wxi","" );
		
		boolean ans = true;
		boolean val;
		
		val = u1.verifyPassword("");
		
		assertEquals(ans, val);
	}
	
	@Test
	public void testPassword3(){
		User u1 = new User("qxr","correct");
		
		
		boolean pass = u1.verifyPassword("incorrect");
		
		assertFalse(pass);
	}
	
	
	@Test
	public void testEquals(){
		User u1 = new User("qxr","correct");
		User u2 = new User("qxr","correct");
		
		boolean ans = true;
		boolean equals = u1.equals(u2);
		
		assertEquals(ans, equals);
	}
	
	@Test
	public void testEquals2(){
		User u1 = new User("qxr","correct");
		User u2 = new User("rxq","correct");
		
		boolean equals = u1.equals(u2);
		
		assertFalse(equals);
	}
	
	@Test
	public void testEquals3(){
		User u1 = new User("qxr","correct");
		User u2 = new User("qxr","incorrect");
		
		boolean ans = false;
		boolean equals = u2.equals(u1);
		
		assertEquals(ans, equals);
	}
}
