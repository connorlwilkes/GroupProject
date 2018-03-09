package Server;

import java.util.ArrayList;
import java.util.List;

public class GameLobby implements Runnable {

    private String lobbyName;
    private int id;
    private int totalScore;
    private List<Player> players;
    private final int maxPlayers = 4;
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

    public synchronized void addPlayer(Player playerToAdd) {
        players.add(playerToAdd);
        if (players.size() == maxPlayers) {
            isFull = true;
            run();
        }
    }

    public synchronized void removePlayer(Player playerToRemove) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(playerToRemove)) {
                players.remove(i);
            }
        }
    }

    public synchronized List<Player> getPlayers() {
        return players;
    }

    public synchronized boolean isFull() {
        return isFull;
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized void addGame(Minigame game){
        games.add(game);
    }

    @Override
    public void run() {

    }

    public synchronized List<Player> getWinningPlayers() {
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
