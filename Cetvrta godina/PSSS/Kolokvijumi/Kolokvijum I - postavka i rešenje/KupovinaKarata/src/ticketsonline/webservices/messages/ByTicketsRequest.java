package ticketsonline.webservices.messages;

public class ByTicketsRequest extends PriceRequest {

    private double deposit;

    public ByTicketsRequest() {
    }

    public ByTicketsRequest(String manifestationName, int ticketsCount, double deposit) {
        super(manifestationName, ticketsCount);
        this.deposit = deposit;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
