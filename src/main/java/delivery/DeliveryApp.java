package delivery;

import delivery.model.*;
import delivery.model.commands.DroneCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DeliveryApp {
    static String input = "src/main/resources/busy_day.in";

    public static void main(String[] args) {
        DeliveryApp app = new DeliveryApp();
        app.start();
    }

    private void start() {
        Model m = parse(Paths.get(input));
        List<DroneCommand> solution = findSolution(m.map, m.warehouses, m.drones, m.orders, m.deadline);
        printSolution(solution);
    }

    private void printSolution(List<DroneCommand> solution) {
        System.out.println(solution.size());
        solution.forEach(c -> System.out.println(c.toString()));
    }

    public final static class Model {

        public final DeliveryMap map;
        public final List<Warehouse> warehouses;
        public final List<Drone> drones;
        public final List<Order> orders;
        public final int deadline;

        public Model(DeliveryMap map, List<Warehouse> warehouses, List<Drone> drones, List<Order> orders, int deadline) {
            this.map = map;
            this.warehouses = warehouses;
            this.drones = drones;
            this.orders = orders;
            this.deadline = deadline;
        }
    }

    public Model parse(Path input) {
        DeliveryMap map;
        List<Warehouse> warehouses;
        List<Drone> drones;
        List<Order> orders;
        List<ProductType> productTypes;
        int deadline;

        try {
            BufferedReader br = Files.newBufferedReader(input);

            /*
            The first section of the file describes the parameters of the simulation. This section contains a single line
            containing the following natural numbers separated by single spaces:
              ○ number of rows in the area of the simulation (1 ≤ number of rows ≤ 10000)
              ○ number of columns in the area of the simulation (1 ≤ number of columns ≤ 10000)
              ○ D ­ number of drones available (1 ≤ D ≤ 1000)
              ○ deadline of the simulation (1 ≤ deadline of the simulation ≤ 1000000)
              ○ maximum load of a drone (1 ≤ maximum load of a drone ≤ 10000)
            */
            String[] parts;
            parts = br.readLine().split("\\s");
            int rows = Integer.parseInt(parts[0]);
            int columns = Integer.parseInt(parts[1]);
            int numberOfDrones = Integer.parseInt(parts[2]);
            deadline = Integer.parseInt(parts[3]);
            int maximumLoadOfADrone = Integer.parseInt(parts[4]);


            map = new DeliveryMap(rows, columns);

            /*
            The next section of the file describes the weights of the products available for orders. This section
            contains:
              ● a line containing the following natural number:
                ○ P ­ the number of different product types available in warehouses (1 ≤ P ≤ 10000)
              ● a line containing P natural numbers separated by single spaces denoting weights of subsequent
                products types, from product type 0 to product type P ­ 1. For each weight, 1 ≤ weight ≤ maximum load of a drone .
             */
            parts = br.readLine().split("\\s");
            int numberOfDifferentProductTypes = Integer.parseInt(parts[0]);
            productTypes = new ArrayList<>(numberOfDifferentProductTypes);
            parts = br.readLine().split("\\s");
            for(int id=0; id < numberOfDifferentProductTypes; id++) {
                int weight = Integer.parseInt(parts[id]);
                productTypes.add(new ProductType(id, weight));
            }

            /*
            The next section of the file describes the warehouses and availability of individual product types at
            each warehouse. This section contains:
             ● a line containing the following natural number:
               ○ W ­ the number of warehouses (1 ≤ W ≤ 10000)
             ● two lines for each warehouse, each two lines describing the subsequent warehouses from
               warehouse 0 to warehouse W − 1 :
               ○ a line containing two natural numbers separated by a single space: the row and the column
                 in which the warehouse is located (0 ≤ row < number of rows; 0 ≤ column < number of columns)
               ○ a line containing P natural numbers separated by single spaces: number of items of the
                 subsequent product types available at the warehouse, from product type 0 to product type
                 P − 1 . For each product type, 0 ≤ number of items ≤ 10000 holds.
             */
            parts = br.readLine().split("\\s");
            int numberOfWarehouses = Integer.parseInt(parts[0]);
            warehouses = new ArrayList<>(numberOfWarehouses);

            for(int warehouseId=0; warehouseId < numberOfWarehouses; warehouseId++) {
                parts = br.readLine().split("\\s");
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);

                Warehouse warehouse = new Warehouse(new Location(c,r),warehouseId);
                warehouses.add(warehouse);

                parts = br.readLine().split("\\s");
                for(int productTypeId=0; productTypeId < numberOfDifferentProductTypes; productTypeId++) {
                    int quantity = Integer.parseInt(parts[productTypeId]);
                    warehouse.addProducts(productTypes.get(productTypeId), quantity);
                }
            }

            drones = new ArrayList<>(numberOfDrones);
            Location droneStartLocation = warehouses.get(0).location;
            for(int droneId=0; droneId < numberOfDrones; droneId++) {
                drones.add(new Drone(droneId, maximumLoadOfADrone, droneStartLocation));
            }

            /*
            The next section of the file describes the customer orders. This section contains:
              ● a line containing the following natural number:
                ○ C ­ the number of customer orders (1 ≤ C ≤ 10000)
              ● three lines for each order, each three lines describing the subsequent orders from order 0 to C ­ 1:
                ○ a line containing two natural numbers separated by a single space: the row of the delivery
                  cell and the column of the delivery cell
                  (0 ≤ row < number of rows; 0 ≤ column < number of columns)
                ○ a line containing one natural number Li
                  ­ the number of the ordered product items
                  (1 ≤ L 0000)
                  i < 1
                ○ a line containing Li integers separated by single spaces: the product types of the individual
                  product items. For each of the product types, 0 ≤ product type < P holds.
            */
            parts = br.readLine().split("\\s");
            int numberOfOrders = Integer.parseInt(parts[0]);
            orders = new ArrayList<>(numberOfOrders);
            for(int orderId=0; orderId < numberOfOrders; orderId++) {
                parts = br.readLine().split("\\s");
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                Location orderLocation = new Location(r, c);
                Order order = new Order(orderId, orderLocation);

                parts = br.readLine().split("\\s");
                int numberOfOrderedProductItems = Integer.parseInt(parts[0]);

                parts = br.readLine().split("\\s");
                for(int i=0; i < numberOfOrderedProductItems; i++) {
                    int productTypeId = Integer.parseInt(parts[i]);
                    ProductType productType = productTypes.get(productTypeId);
                    order.addProductItem(productType);
                }

                orders.add(order);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Model(map, warehouses, drones, orders, deadline);
    }

    public List<DroneCommand> findSolution(DeliveryMap map, List<Warehouse> warehouses, List<Drone> drones, List<Order> orders, int deadline) {
        DroneRouter droneRouter = new DroneRouter(map, warehouses, drones, orders, deadline);
        return droneRouter.calculate();
    }

}
