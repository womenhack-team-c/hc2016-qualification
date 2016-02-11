package delivery;

import java.io.*;
import delivery.model.DeliveryMap;
import delivery.model.Drone;
import delivery.model.Order;
import delivery.model.Warehouse;
import delivery.model.commands.DroneCommand;
import java.util.List;


public class DeliveryApp {
    static String input = "src/main/resources/busy_day.in";

    public static void main(String[] args) {
        DeliveryApp app = new DeliveryApp();
        app.start(input);
    }

    public void start(String input) {

        String row = null;
        String col = null;
        String drones = null;
        String deadline = null;
        String maxLoad = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(input)));

            for (int i = 0; i < 5; i++) {
                row = br.readLine();
                System.out.println(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public String readFile(){
//        String inputFile = null;
//        BufferedReader br = new BufferedReader(new FileReader(new File(input)));
//
//        try{
////            while ((inputFile = br.readLine()) != null) {
////                System.out.println(inputFile);
////            }
//            System.out.printf(inputFile);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return inputFile = br.readLine();
//
//    }

    public List<DroneCommand> findSolution(DeliveryMap map, List<Warehouse> warehouses, List<Drone> drones, List<Order> orders) {
        DroneRouter droneRouter = new DroneRouter(map, warehouses, drones, orders);
        List<DroneCommand> droneCommands = droneRouter.calculate();
        return droneCommands;
    }

}
