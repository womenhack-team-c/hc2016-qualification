package delivery;

import java.io.*;
import delivery.model.DeliveryMap;
import delivery.model.Drone;
import delivery.model.Order;
import delivery.model.Warehouse;
import delivery.model.commands.DroneCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


public class DeliveryApp {
    static String input = "src/main/resources/busy_day.in";
    public String[] fileArray = new String[5000];

    public static void main(String[] args) {
        DeliveryApp app = new DeliveryApp();
        app.readFile();

        int row = app.getRow();
        int col = app.getCol();

         DeliveryMap map = new DeliveryMap(row, col);
//        List<Warehouse> warehouses;
        List<Drone> drones = app.getDrone();
//        List<Order> orders;

//        app.findSolution(map, warehouses, drones, orders);
    }

    public String[] readFile(){
        String inputFile = null;

        try{
            BufferedReader br = new BufferedReader(new FileReader(new File(input)));
            int i = 0;
            while ((inputFile = br.readLine()) != null){
                fileArray[i] = inputFile;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileArray;
    }

    public int getRow(){
        String rowString = fileArray[0];
        String[] rowSplit = rowString.split("\\s+");
        int row = Integer.parseInt(rowSplit[0]);

        System.out.println(rowSplit[0]);

        return row;
    }

    public int getCol(){
        String colString = fileArray[1];
        String[] colSplit = colString.split("\\s+");
        int col = Integer.parseInt(colSplit[0]);

        System.out.println(colSplit[0]);

        return col;
    }

    public List<Drone> getDrone(){
        String droneString = fileArray[2];
        String[] droneSplit = droneString.split("\\s+");

        int numberOfDrones = Integer.parseInt(droneSplit[0]);
        int maxPayload = 10;
        List<Drone> drones = new ArrayList<>(numberOfDrones);

        for(int id=0; id < numberOfDrones; id++) {
            drones.add(new Drone(id, maxPayload ));
        }

        return drones;
    }

    public List<DroneCommand> findSolution(DeliveryMap map, List<Warehouse> warehouses, List<Drone> drones, List<Order> orders) {
        DroneRouter droneRouter = new DroneRouter(map, warehouses, drones, orders);
        List<DroneCommand> droneCommands = droneRouter.calculate();
        return droneCommands;
    }

}
