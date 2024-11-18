package Models;

public class InboxItem {
    private int chatId;
    private String message;
    private int sender;
    private String senderName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public InboxItem() {
        super();
    }

    public InboxItem(int chatId, String message, int sender, String senderName) {
        super();
        this.chatId = chatId;
        this.message = message;
        this.sender = sender;
        this.senderName = senderName;
    }
}
