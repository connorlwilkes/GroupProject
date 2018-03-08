package Server;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameLobby implements Runnable {

    private String password;
    private String lobbyName;
    private int id;
    private int totalScore;
    private GameOwner owner;
    private List<Player> players;
    private List<Minigame> games;
    private ChatRoom room;
    private boolean isFull;

    public GameLobby(int id) {
        this.lobbyName = "Lobby " + id;
        this.id = id;
        totalScore = 0;
        players = new ArrayList<>();
        games = new ArrayList<>();
        this.isFull = false;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getPassword() {
        return password;
    }

    public void addPlayer(Player playerToAdd) {
        players.add(playerToAdd);
    }

    public void removePlayer(Player playerToRemove) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(playerToRemove)) {
                players.remove(i);
            }
        }
    }

    public void setOwner(GameOwner owner) {
        this.owner = owner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void run() {

    }

    public List<Player> getWinningPlayers() {
        List<Player> winningPlayers = new ArrayList<>();
        int maxScore = 0;
        for (Player player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
            }
        }
        for (Player player : players) {
            if (player.getScore() == maxScore) {
                winningPlayers.add(player);
            }
        }
        return winningPlayers;
    }
}
