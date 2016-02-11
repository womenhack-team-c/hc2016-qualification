package delivery;


import delivery.model.*;
import delivery.model.commands.DroneCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static delivery.model.commands.DroneCommand.deliver;
import static delivery.model.commands.DroneCommand.load;

public class DroneRouter {
    private final DeliveryMap map;
    private final List<Warehouse> warehouses;
    private final List<Drone> drones;
    private final List<Order> orders;

    public DroneRouter(DeliveryMap map, List<Warehouse> warehouses, List<Drone> drones, List<Order> orders) {
        this.map = map;
        this.warehouses = warehouses;
        this.drones = drones;
        this.orders = orders;
    }

    public List<DroneCommand> calculate() {
        return naiveSolution();
    }


    public List<DroneCommand> naiveSolution() {
        List<DroneCommand> commands = new ArrayList<>(100);

        orders.forEach( order -> {
            Drone drone = drones.get(0);
            Warehouse warehouse = warehouses.get(0);

            order.items.entrySet().forEach( entry -> {
                ProductType productType = entry.getKey();
                Integer quantity = entry.getValue();
                commands.add(load(drone, warehouse, productType, quantity ));
            });

            order.items.entrySet().forEach( entry -> {
                ProductType productType = entry.getKey();
                Integer quantity = entry.getValue();
                commands.add(deliver(drone, order, productType, quantity ));
            });
        });

        return commands;
    }
}
