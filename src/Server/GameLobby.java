package Server;

import java.util.ArrayList;
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
    private boolean isRunning;

    public GameLobby(int id) {
        this.lobbyName = "Lobby " + id;
        this.id = id;
        totalScore = 0;
        players = new ArrayList<>();
        games = new ArrayList<>();
        this.isFull = false;
        this.isRunning = false;
        room = new ChatRoom();
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getPassword() {
        return password;
    }

    public void addPlayer(Player playerToAdd) {
        players.add(playerToAdd);
        if (players.size() == 4) {
            isFull = true;
            this.run();
        }
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

    public boolean isFull() {
        return isFull;
    }

    public boolean isRunning() {
        return isRunning;
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
