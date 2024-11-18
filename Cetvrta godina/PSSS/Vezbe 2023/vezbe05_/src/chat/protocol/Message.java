package chat.protocol;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private String from;

    public Message() {
    }

    public Message(String text, String from) {
        this.text = text;
        this.from = from;
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getFrom() {
        return from;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(baos);
        encoder.writeObject(this);
        encoder.close();
        String s = new String(baos.toByteArray());
        s = s.replace("\n", " ");
        return s;
    }
}
