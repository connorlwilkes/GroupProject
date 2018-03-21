package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Class to test methods in GameLobby class
 * @author Florence
 * @version 21/03/2018
 */
public class GameLobbyTest {
	
	@Test
	public void testIsFull1(){
		GameLobby g1 = new GameLobby(1);
		
		boolean expected = false; //no players have been added to the game lobby so IsFull should return false
		boolean actual;
		
		actual = g1.isFull();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIsFull2(){
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		
		boolean expected = false; //2 players have been added to the game lobby so IsFull should still return false
		boolean actual;
		
		actual = g1.isFull();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIsFull3(){
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		boolean expected = true; //3 players have been added to the game lobby so now IsFull should return true
		boolean actual;
		
		actual = g1.isFull();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void IsRunningTest(){
		GameLobby g1 = new GameLobby(1);
		
		boolean expected = false; 
		boolean actual;
		
		actual = g1.isRunning();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void removePlayer() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		g1.removePlayer(p3);
		
		boolean expected = false; //3 players have been added to the game lobby but 1 has been removed 
								 //so IsFull should return true
		boolean actual;
		
		actual = g1.isFull();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestGetScores1() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		p1.setScore(1);
		p2.setScore(2);
		p3.setScore(3);
		
		String[] expected = {"get-scores", "qxr: 1","yxh: 2","fxd: 3"};
		String[] actual = g1.getScores();
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestGetScores2() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		
		String[] expected = {"get-scores", "qxr: 0","yxh: 0","fxd: 0"}; //before any points are earned, all players have zero score.
		String[] actual = g1.getScores();
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestWinners1() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		p1.setScore(1); //player 1 has the winning score
		p2.setScore(0);
		p3.setScore(0);

		List<Player> actual = g1.getWinningPlayers();
		
		List<Player> expected = new ArrayList<Player>();
		expected.add(p1);
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestWinners2() {
		
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		p1.setScore(3); //player 1 has the winning score
		p2.setScore(3);
		p3.setScore(1);

		List<Player> actual = g1.getWinningPlayers();
		
		List<Player> expected = new ArrayList<Player>();
		expected.add(p1);
		
		assertNotEquals(expected,actual);
	}

}
