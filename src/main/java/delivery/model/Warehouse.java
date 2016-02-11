package delivery.model;


import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Cell location;
    private Map<ProductType, Integer> products = new HashMap<>();
}
