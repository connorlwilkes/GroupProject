package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Class to test methods in Minigame class
 * @author Florence
 * @version 21/03/2018
 */
public class MinigameTest {
	
	@Test
	public void TestRoundNumber() {
		Minigame m1 = new Minigame(null, null);
		int num = 2;
		m1.setRoundNumber(num);
		
		assertEquals(2, m1.getRoundNumber());
		
	}
	
	@Test
	public void TestRoundNumber2() {
		Minigame m1 = new Minigame(null, null);
		int num = 0;
		m1.setRoundNumber(num);
		
		assertEquals(0, m1.getRoundNumber());
		
	}
	
	@Test
	public void TestAddScore1() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null, players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		players.add(p1);
		
		int Add300 = 300;
		int Add1000 = 1000;
		
		
		m1.addScore(Add300, u1.getUsername());
		m1.addScore(Add1000, u1.getUsername());
		
		int expected = 1300;
		
		assertEquals(expected, p1.getScore());
		
		
	}
	
	@Test
	public void TestAddScore2() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null, players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		players.add(p1);
		
		int AddZero = 0;
		
		m1.addScore(AddZero, u1.getUsername());
		
		int expected = 0;
		
		assertEquals(expected, p1.getScore());
		
	}
	
	@Test
	public void TestAddScore3() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null, players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		players.add(p1);
		
		int Add = 115;
		
		m1.addScore(Add, u1.getUsername());
		
		int expected = 117;
		
		assertNotEquals(expected, p1.getScore());
		
	}
	
	@Test
	public void TestGetPlayers1() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null, players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		List<Player> expected = new ArrayList<>();
		expected.add(p1);
		expected.add(p2);
		expected.add(p3);
		
		
		assertEquals(expected, m1.getPlayers());
		
	}
	
	@Test
	public void TestGetPlayers2() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null, players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		players.add(p1);
		players.add(p3);
		players.add(p2);
		
		List<Player> expected = new ArrayList<>();
		expected.add(p1);
		expected.add(p2);
		expected.add(p3);
		
		assertNotEquals(expected, m1.getPlayers());
		
	}
	
		@Test
	public void TestWinner1() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null, players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		players.add(p1);
		players.add(p3);
		players.add(p2);
		
		p1.setScore(10);
		
		List<Player> expectedWinners = new ArrayList<>();
		expectedWinners.add(p1);
		
		assertNotEquals(expectedWinners, m1.winner());
		
	}
	
	@Test
	public void TestWinner2() {
		GameLobby g1 = new GameLobby(1);
		Socket sock1 = new Socket();
		ServerThread s1 = new ServerThread(sock1);
		List<Player> players = new ArrayList<>();
		Minigame m1 = new Minigame(null,players);
		
		User u1 = new User("qxr","password1");
		Player p1 = new Player(s1, g1, u1);
		
		User u2 = new User("yxh","password2");
		Player p2 = new Player(s1, g1, u2);
		
		User u3 = new User("fxd","password3");
		Player p3 = new Player(s1, g1, u3);
		
		players.add(p1);
		players.add(p3);
		players.add(p2);
		
		p1.setScore(10);
		p2.setScore(10);
		
		List<Player> expectedWinners = new ArrayList<>();
		expectedWinners.add(p1);
		expectedWinners.add(p2);
		
		assertNotEquals(expectedWinners, m1.winner());
		
	}

}
