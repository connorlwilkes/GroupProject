package Server;

public class GameOwner extends Player {

    private GameLobby gameLobby;

    public GameOwner(ServerThread client, GameLobby gameLobby) {
        super(client);
        this.gameLobby = gameLobby;
    }

    public GameOwner(ServerThread client) {
        super(client);
    }

    public void setGameLobby(GameLobby gameLobby) {
        this.gameLobby = gameLobby;
    }
}
