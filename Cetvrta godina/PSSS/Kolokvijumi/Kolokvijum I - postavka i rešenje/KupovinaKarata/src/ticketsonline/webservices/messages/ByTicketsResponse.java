package ticketsonline.webservices.messages;

import ticketsonline.ticketstore.Ticket;

import java.io.Serializable;
import java.util.List;

public class ByTicketsResponse implements Serializable {

    List<Ticket> tickets;

    public ByTicketsResponse() {
    }

    public ByTicketsResponse(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
