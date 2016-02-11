package delivery;


import delivery.model.DeliveryMap;
import delivery.model.Drone;
import delivery.model.Order;
import delivery.model.Warehouse;
import delivery.model.commands.DroneCommand;

import java.util.ArrayList;
import java.util.List;

import static delivery.model.commands.DroneCommand.deliver;

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

            commands.add(deliver());
        });

        return commands;
    }
}
