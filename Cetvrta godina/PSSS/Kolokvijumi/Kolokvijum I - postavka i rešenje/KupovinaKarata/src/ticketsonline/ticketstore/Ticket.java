package ticketsonline.ticketstore;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private static int generatorId = 1;
    private int id;
    private String manifestationName;
    private Date date;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", manifestationName='" + manifestationName + '\'' +
                ", date=" + date +
                '}';
    }

    public Ticket(String manifestationName) {
        id = generatorId;
        generatorId++;

        this.manifestationName = manifestationName;
        date = new Date();
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManifestationName() {
        return manifestationName;
    }

    public void setManifestationName(String manifestationName) {
        this.manifestationName = manifestationName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
