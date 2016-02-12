package delivery.model;


import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    public Location location;
    private Map<ProductType, Integer> products = new HashMap<>();
    public final int id;

    public Warehouse(Location location, int id) {
        this.id = id;
        this.location = location;
    }

    public void addProducts(ProductType productType, int quantity) {
        products.put(productType, quantity);
    }
}
