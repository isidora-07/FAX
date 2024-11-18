package chat.protocol;

public class PrivateMessage extends Message {
    private String toRecipient;

    public String getToRecipient() {
        return this.toRecipient;
    }

    public void setToRecipient(String toRecipient) {
        this.toRecipient = toRecipient;
    }

    public PrivateMessage() {
    }

    public PrivateMessage(String text, String from, String toRecipient) {
        super(text, from);
        this.toRecipient = toRecipient;
    }

    public PrivateMessage(String text, String toRecipient) {
        super(text);
        this.toRecipient = toRecipient;
    }
}
