package ticketsonline.ticketstore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Store {

    private HashMap<String, Manifestation> manifestations;

    public Store() {
        manifestations = new HashMap<String, Manifestation>();
    }

    public void addNewManifestation(Manifestation manifestation){
        manifestations.put(manifestation.name, manifestation);
    }
    public double getManifestationIncome(String manifestationName){
        Manifestation manifestation =  manifestations.get(manifestationName);
        return manifestation.getTotalIncome();
    }

    public double getTotalPrice(String manifestationName, int ticketsCount){
        return  manifestations.get(manifestationName).getPriceForTickets(ticketsCount);
    }

    public List<Ticket> byTickets(String manifestationName, int ticketsCount, double deposit){
        // NIO resenje svakako koristi jednu nit pa nema konkuretnosti, ovo je pogodno za stream sockete
        // ipak zbog razdvajanja logike od servera korisno je paket napraviti sigurnim,
        // a onda se moze koristiti na raylicite nacine
        synchronized(manifestations){
          if(manifestations.get(manifestationName).byTickets(ticketsCount, deposit)){
              List<Ticket> tickets = new LinkedList<Ticket>();
              for (int i = 0; i < ticketsCount; i++) {
                  tickets.add(new Ticket(manifestationName));
              }
              return tickets;
          }
        }
        return null;
    }

    public List<ManifestationInfo> getAvailableManifestations(){
        List<ManifestationInfo> manifestationInfoList = new LinkedList<ManifestationInfo>();
        for (Manifestation manifestation : manifestations.values()) {
            if (manifestation.getNumberOfAvailableTickets() > 0) {
                manifestationInfoList.add(new ManifestationInfo(manifestation.getName(), manifestation.getNumberOfAvailableTickets()));
            }
        }
        return  manifestationInfoList;
    }
}
