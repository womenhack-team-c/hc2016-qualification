package delivery.model;


import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    public Cell location;
    private Map<ProductType, Integer> products = new HashMap<>();
    public final int id;

    public Warehouse(Cell location, int id) {
        this.id = id;
        this.location = location;
    }
}
