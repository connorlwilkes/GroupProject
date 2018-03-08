package Server;

public class GameOwner extends Player {

    private GameLobby gameLobby;

    public GameOwner(ServerThread client, GameLobby lobby) {
        super(client, lobby);
    }
    public void setGameLobby(GameLobby gameLobby) {
        this.gameLobby = gameLobby;
    }
}
