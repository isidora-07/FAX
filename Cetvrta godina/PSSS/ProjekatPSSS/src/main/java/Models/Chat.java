package Models;

public class Chat {
    private int id;
    private int user1id;
    private int user2id;
    private String user2email;

    public Chat(int id, int user1id, int user2id, String user2email) {
        super();
        this.id = id;
        this.user1id = user1id;
        this.user2id = user2id;
        this.user2email = user2email;
    }

    public Chat() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser1id() {
        return user1id;
    }

    public void setUser1id(int user1id) {
        this.user1id = user1id;
    }

    public int getUser2id() {
        return user2id;
    }

    public void setUser2id(int user2id) {
        this.user2id = user2id;
    }

    public String getUser2email() {
        return user2email;
    }

    public void setUser2email(String user2email) {
        this.user2email = user2email;
    }
}
