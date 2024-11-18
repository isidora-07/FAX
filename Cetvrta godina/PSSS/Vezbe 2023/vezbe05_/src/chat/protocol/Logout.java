package chat.protocol;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Logout implements Serializable {
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
