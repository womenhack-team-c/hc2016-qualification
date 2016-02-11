package delivery.model;

public class Drone {
    public final int id;

    public Drone(int id, int maxPayload) {
        this.maxPayload = maxPayload;
        this.id = id;
    }
    private Cell location = new Cell();
    private final int maxPayload;
}
