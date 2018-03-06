package Server.GameLogic;

import Server.Server;

public class Player {

    private Server.ServerThread client;
    private int score;

    public Player (Server.ServerThread client) {
        this.client = client;
        this.score = 0;
    }

    public Server.ServerThread getClient() {
        return client;
    }

    public int getScore() {
        return score;
    }
}
