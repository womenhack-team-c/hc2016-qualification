package delivery.model;

public class Drone {
    public final int id;

    public Drone(int id, int maxPayload, Location location) {
        this.id = id;
        this.maxPayload = maxPayload;
        this.location = location;
    }
    private Location location;
    private final int maxPayload;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
