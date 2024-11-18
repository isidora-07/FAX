package Models;

public class ChatMessage {
    private int id;
    private int senderId;
    private int reciverId;
    private String message;
    private int chatId;

    public ChatMessage(int id, int senderId, int reciverId, String message, int chatId) {
        super();
        this.id = id;
        this.senderId = senderId;
        this.reciverId = reciverId;
        this.message = message;
        this.chatId = chatId;
    }

    public ChatMessage() {
        super();
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
