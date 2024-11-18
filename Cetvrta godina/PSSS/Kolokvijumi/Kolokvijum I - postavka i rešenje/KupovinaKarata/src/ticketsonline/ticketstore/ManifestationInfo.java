package ticketsonline.ticketstore;

public class ManifestationInfo {
    private String Name;
    private int availableTicketsCount;

    public ManifestationInfo(String name, int availableTicketsCount) {
        Name = name;
        this.availableTicketsCount = availableTicketsCount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAvailableTicketsCount() {
        return availableTicketsCount;
    }

    public void setAvailableTicketsCount(int availableTicketsCount) {
        this.availableTicketsCount = availableTicketsCount;
    }

    @Override
    public String toString() {
        return "ManifestationInfo{" +
                "Name='" + Name + '\'' +
                ", availableTicketsCount=" + availableTicketsCount +
                '}';
    }
}
