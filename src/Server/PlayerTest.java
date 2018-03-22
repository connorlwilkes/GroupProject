package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void testGetClient1() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		ServerThread actual = p1.getClient();
		
		assertEquals(s1,actual);
	}
	
	@Test
	public void testGetClient2() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		ServerThread s2 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		ServerThread actual = p1.getClient();
		
		assertNotEquals(s2,actual);
	}
	
	@Test
	public void testScoreGet1() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		p1.setScore(16);
		
		int expected = 16;
		int actual = p1.getScore();
		
		assertEquals(actual,expected);
	}
	
	@Test
	public void testScoreGet2() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		p1.setScore(70);
		
		int expected =0;
		int actual = p1.getScore();
		
		assertNotEquals(actual,expected);
	}
	
	@Test
	public void testSetQM() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		p1.setQuestionMaster(false);
		
		boolean expected = true;
		boolean actual = p1.isHasBeenQuestionMaster();
		
		assertNotEquals(actual,expected);
	}
	
	@Test
	public void testGetLobby1() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		g1.addPlayer(p1);
		
		GameLobby expected =g1;
		GameLobby actual = p1.getLobby();
		
		assertEquals(actual,expected);
	}
	
	@Test
	public void testGetLobby2() {
		
		GameLobby g1 = new GameLobby(1);
		GameLobby g2 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		g1.addPlayer(p1);
		
		GameLobby expected =g2;
		GameLobby actual = p1.getLobby();
		
		assertNotEquals(actual,expected);
	}
	
	@Test
	public void testGetUser1() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User expected =u1;
		User actual = p1.getUser();
		
		assertEquals(actual,expected);
	}
	
	@Test
	public void testGetUser2() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User expected =u2;
		User actual = p1.getUser();
		
		assertNotEquals(actual,expected);
	}
	

}
