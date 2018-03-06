package Server.GameLogic;

import Server.Server;

public class GameOwner extends Player {

    private GameLobby gameLobby;

    public GameOwner(Server.ServerThread client, GameLobby gameLobby) {
        super(client);
        this.gameLobby = gameLobby;
    }

    public GameOwner(Server.ServerThread client) {
        super(client);
    }

    public void setGameLobby(GameLobby gameLobby) {
        this.gameLobby = gameLobby;
    }
}
