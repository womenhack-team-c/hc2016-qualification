package delivery.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public int id;
    public Map<ProductType, Integer> items = new HashMap<>();

    public Order(int id) {
        this.id = id;
    }

}
