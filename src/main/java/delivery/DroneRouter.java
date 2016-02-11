package delivery;


import delivery.model.DeliveryMap;
import delivery.model.Drone;
import delivery.model.Order;
import delivery.model.Warehouse;
import delivery.model.commands.DroneCommand;

import java.util.ArrayList;
import java.util.List;

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
        List<DroneCommand> commands = new ArrayList<>(100);

        return commands;
    }
}
