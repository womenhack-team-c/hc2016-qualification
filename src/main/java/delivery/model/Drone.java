package delivery.model;

public class Drone {
    public Drone(int maxPayload) {
        this.maxPayload = maxPayload;
    }
    private Cell location = new Cell();
    private final int maxPayload;
}
