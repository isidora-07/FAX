package ticketsonline.webservices.messages;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Util {

    public static String toXml(Object object){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(baos);
        encoder.writeObject(object);
        encoder.close();

        String s = new String(baos.toByteArray());
        s = s.replace("\n", " ");
        return s;
    }

    public static Object fromXml(String xmlString){
        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xmlString.getBytes()));
        return decoder.readObject();
    }
}
