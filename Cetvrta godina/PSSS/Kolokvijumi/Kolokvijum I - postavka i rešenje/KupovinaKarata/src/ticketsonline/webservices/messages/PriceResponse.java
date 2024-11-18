package ticketsonline.webservices.messages;

import java.io.Serializable;

public class PriceResponse implements Serializable {

    private double totalPrice;

    public PriceResponse(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PriceResponse() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
