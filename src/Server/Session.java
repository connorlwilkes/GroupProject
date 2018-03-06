package Server;

public class Session {

    private User user;
    private ServerThread thread;

    public Session(User user, ServerThread thread) {
        this.user = user;
        this.thread = thread;
    }
}
