package Server;

/**
 * GameOwner class for the game. This is a player that is in charge of a game lobby. They can remove players and moderate
 * the game.
 * @author Florence
 * @version 9/3/2018
 */
public class GameOwner extends Player {

    private GameLobby gameLobby;

    /**
     * Constructor for the GameOwner class
     *
     * @param client client associated with the GameOwner
     * @param lobby  lobby the GameOwner owns
     */
    public GameOwner(ServerThread client, GameLobby lobby, User user) {
        super(client, lobby, user);
    }

    /**
     * Removes a player from the gamelobby
     *
     * @param playerToRemove player to remove
     */
    public void RemovePlayer(Player playerToRemove) {
        gameLobby.removePlayer(playerToRemove);
    }
}
