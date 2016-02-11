package delivery.model.commands;

public abstract class DroneCommand {
    public static DroneCommand load() {
        return new LoadCommand();
    }
    public static DroneCommand unload() {
        return new UnloadCommand();
    }
    public static DroneCommand deliver() {
        return new DeliverCommand();
    }
    public static DroneCommand waitCommand() {
        return new WaitCommand();
    }
}


/**
 * Load: Moves the specified number of items of the specified product type from a warehouse to the
 * drone’s inventory. If the drone isn’t at the warehouse it will fly there using the shortest path before
 * loading the product items. The requested number of items of the specified product type must be
 * available in the warehouse. The total weight of the items in the drone’s inventory after the load
 * cannot be bigger than the drone’s maximum load.
 */
class LoadCommand extends DroneCommand {

}

/**
 * Deliver: Delivers the specified number of items of the specified product type to a customer. If the
 * drone isn’t at the destination it will fly there using the shortest path before delivering the product
 * items. The drone must have the requested number of items of the specified product type in its
 * inventory.
 * Each drone can also be given the following advanced commands. These commands are not necessary to
 * solve the problem, but you can use them to further improve your solution.
 */
class DeliverCommand extends  DroneCommand {

}


/**
 * Unload: Moves the specified number of items of the specified product type from drone’s inventory to
 * a warehouse. If the drone isn’t at the warehouse it will fly there using the shortest path before
 * unloading the product items. The drone must have the requested number of items of the specified
 * product type in its inventory.
*/
class UnloadCommand extends  DroneCommand {

}

/**
 *  Wait: Waits the specified number of turns in the drone's current location.
 */
class WaitCommand extends DroneCommand {

}

