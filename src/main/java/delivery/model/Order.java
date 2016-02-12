package delivery.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public final int id;
    public final Location location;
    public Map<ProductType, Integer> items = new HashMap<>();

    public Order(int id, Location location) {
        this.id = id;
        this.location = location;
    }

    public void addProductItem(ProductType productType) {
        int quantity = items.getOrDefault(productType, 0);
        items.put(productType, quantity);
    }
}
