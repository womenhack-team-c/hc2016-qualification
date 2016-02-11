package delivery;

import delivery.model.Drone;
import delivery.model.Order;
import delivery.model.Warehouse;
import delivery.model.commands.DroneCommand;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class DeliveryApp {
    public static void main(String[] args) {
        DeliveryApp app = new DeliveryApp();
        app.start(System.in, System.out, System.err);
    }

    public void start(InputStream in, PrintStream out, PrintStream err) {

    }





    public List<DroneCommand> findSolution(List<Warehouse> warehouses, List<Drone> drones, List<Order> orders) {

    }
}
