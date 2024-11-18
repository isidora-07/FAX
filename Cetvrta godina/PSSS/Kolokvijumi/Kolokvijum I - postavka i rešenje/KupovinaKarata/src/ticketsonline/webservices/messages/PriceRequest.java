package ticketsonline.webservices.messages;

import java.io.Serializable;

public class PriceRequest implements Serializable {

    private String manifestationName;
    private int ticketsCount;

    public PriceRequest() {
    }

    public PriceRequest(String manifestationName, int ticketsCount) {
        this.manifestationName = manifestationName;
        this.ticketsCount = ticketsCount;
    }

    public String getManifestationName() {
        return manifestationName;
    }

    public void setManifestationName(String manifestationName) {
        this.manifestationName = manifestationName;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }
}
