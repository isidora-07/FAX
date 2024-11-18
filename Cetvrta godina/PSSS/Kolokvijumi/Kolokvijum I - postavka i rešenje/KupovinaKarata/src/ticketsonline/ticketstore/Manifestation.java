package ticketsonline.ticketstore;

public class Manifestation {
    String name;
    double price;
    int totalNumberOfTickets;

    int soldTicketsCount = 0;

    public Manifestation(String name, double price, int totalNumberOfTickets) {
        this.name = name;
        this.price = price;
        this.totalNumberOfTickets = totalNumberOfTickets;
    }

    @Override
    public String toString() {
        return "Manifestation{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", totalNumberOfTickets=" + totalNumberOfTickets +
                ", soldTicketsCount=" + soldTicketsCount +
                '}';
    }

    public int getNumberOfAvailableTickets(){
        return totalNumberOfTickets - soldTicketsCount;
    }
    public double getTotalIncome(){
        return soldTicketsCount * price;
    }
    public double getPriceForTickets(int count){
        return count * price;
    }

    public Boolean byTickets(int count, double deposit){
        if (count <= getNumberOfAvailableTickets() && deposit == getPriceForTickets(count) ){
            soldTicketsCount = soldTicketsCount + count;
            return true;
        }
        return  false;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public void setTotalNumberOfTickets(int totalNumberOfTickets) {
        this.totalNumberOfTickets = totalNumberOfTickets;
    }
}
