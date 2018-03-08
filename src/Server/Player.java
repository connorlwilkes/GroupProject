package Server;

public class Player {

    private ServerThread client;
    private int score;
    private boolean isQuestionMaster;
    private GameLobby lobby;

    public Player(ServerThread client, GameLobby lobby) {
        this.client = client;
        this.score = 0;
        this.isQuestionMaster = false;
        this.lobby = lobby;
    }

    public ServerThread getClient() {
        return client;
    }

    public int getScore() {
        return score;
    }

    public void setClient(ServerThread client) {
        this.client = client;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isQuestionMaster() {
        return isQuestionMaster;
    }

    public void setQuestionMaster(boolean questionMaster) {
        isQuestionMaster = questionMaster;
    }
}
