package delivery.model.commands;

import delivery.model.Drone;
import delivery.model.Order;
import delivery.model.ProductType;
import delivery.model.Warehouse;

public abstract class DroneCommand {
    public static DroneCommand load(Drone drone, Warehouse warehouse, ProductType productType, int quanity) {
        return new LoadCommand(drone, warehouse, productType, quanity);
    }
    public static DroneCommand unload(Drone drone, Warehouse warehouse, ProductType productType, int quanity) {
        return new UnloadCommand(drone, warehouse, productType, quanity);
    }
    public static DroneCommand deliver(Drone drone, Order order, ProductType productType, int quanity) {
        return new DeliverCommand(drone, order, productType, quanity);
    }
    public static DroneCommand waitCommand(Drone drone, int  numberOfTurns) {
        return new WaitCommand(drone, numberOfTurns);
    }
}


/**
 * Load: Moves the specified number of items of the specified product type from a warehouse to the
 * drone’s inventory. If the drone isn’t at the warehouse it will fly there using the shortest path before
 * loading the product items. The requested number of items of the specified product type must be
 * available in the warehouse. The total weight of the items in the drone’s inventory after the load
 * cannot be bigger than the drone’s maximum load.
 * the ID of the drone that the command is for
 ● the command tag ­ a single character, either ‘L’ (for load) or ‘U’ (for unload),
 ● the ID of the warehouse from which we load items / to which we unload items
 ● the ID of the product type
 ● the number of items of the product type to be loaded or unloaded ­ a positive integer
 */
class LoadCommand extends DroneCommand {
    private static final String L = "L";
    private final Drone drone;
    private final Warehouse warehouse;
    private final ProductType productType;
    private final int quanity;

    public LoadCommand(Drone drone, Warehouse warehouse, ProductType productType, int quanity) {
        this.drone = drone;
        this.warehouse = warehouse;
        this.productType = productType;
        this.quanity = quanity;
    }

    @Override
    public String toString() {
        return String.format("{0} {0} {0} {0} {0}", drone.id, L, warehouse.id, productType.id, quanity);
    }
}

/**
 * Deliver: Delivers the specified number of items of the specified product type to a customer. If the
 * drone isn’t at the destination it will fly there using the shortest path before delivering the product
 * items. The drone must have the requested number of items of the specified product type in its
 * inventory.
 * Each drone can also be given the following advanced commands. These commands are not necessary to
 * solve the problem, but you can use them to further improve your solution.
 * the ID of the drone that the command is for
 ● the command tag ­ single character ‘D’
 ● the ID of the customer order we are delivering items for
 ● the ID of the product type
 ● the number of items of the product type to be delivered ­ a positive integer
 */
class DeliverCommand extends  DroneCommand {
    private static final String D = "D";
    private final Drone drone;
    private final Order order;
    private final ProductType productType;
    private final int quanity;

    public DeliverCommand(Drone drone, Order order, ProductType productType, int quanity) {
        this.drone = drone;
        this.order = order;
        this.productType = productType;
        this.quanity = quanity;
    }

    @Override
    public String toString() {
        return String.format("{0} {0} {0} {0} {0}", drone.id, D, order.id, productType.id, quanity);
    }
}


/**
 * Unload: Moves the specified number of items of the specified product type from drone’s inventory to
 * a warehouse. If the drone isn’t at the warehouse it will fly there using the shortest path before
 * unloading the product items. The drone must have the requested number of items of the specified
 * product type in its inventory.
*/
class UnloadCommand extends  DroneCommand {
    private static final String U = "U";
    private final Drone drone;
    private final Warehouse warehouse;
    private final ProductType productType;
    private final int quanity;

    public UnloadCommand(Drone drone, Warehouse warehouse, ProductType productType, int quanity) {
        this.drone = drone;
        this.warehouse = warehouse;
        this.productType = productType;
        this.quanity = quanity;
    }

    @Override
    public String toString() {
        return String.format("{0} {0} {0} {0} {0}", drone.id, U, warehouse.id, productType.id, quanity);
    }
}

/**
 *  Wait: Waits the specified number of turns in the drone's current location.
 *  the ID of the drone that the command is for
 ● the command tag ­ single character ‘W’
 ● the number of turns for which the drone needs to wait ­ a positive integer
 */
class WaitCommand extends DroneCommand {
    private static final String W = "U";
    private final Drone drone;
    private final int numberOfTurns;

    public WaitCommand(Drone drone, int  numberOfTurns) {

        this.drone = drone;
        this.numberOfTurns = numberOfTurns;
    }

    @Override
    public String toString() {
        return String.format("{0} {0} {0}", drone.id, W, numberOfTurns);
    }
}

