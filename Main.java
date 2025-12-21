public class Main {
    public static void main(String[] args) {

        // 1. Create map and base position
        Map map = new Map(); // make sure isAllowed() is implemented
        Position base = new Position(0, 0);

        // 2. Create ControlCenter
        ControlCenter controlCenter = new ControlCenter(base, map);

        // 3. Add drones (StandardDrone is a concrete subclass)
        Drone drone1 = new StandardDrone(base, "Model A", 50, 5);
        Drone drone2 = new StandardDrone(base, "Model B", 60, 6);
        controlCenter.addDrone(drone1);
        controlCenter.addDrone(drone2);

        // 4. Create delivery items
        Deliverable item1 = new StandardPackage(1.0 , new Position( 5 , 5 ));
        Deliverable item2 = new StandardPackage(4.0 , new Position( 8, 3));
        Deliverable item3 = new StandardPackage(1.5 , new Position(2, 7));

        // 5. Create orders with delivery items
        Order order1 = new Order("Client 1", item1, "NORMAL", 100);
        Order order2 = new Order("Client 2", item2, "EXPRESS", 150);
        Order order3 = new Order("Client 3", item3, "NORMAL", 80);

        // 6. Add orders to pending orders list
        controlCenter.addOrder(order1);
        controlCenter.addOrder(order2);
        controlCenter.addOrder(order3);

        // 7. Create Simulator and run 8-hour simulation
        Simulator simulator = new Simulator(controlCenter);
        simulator.runSimulation();

        // 8. Print final statistics
        System.out.println("\n=== Final Statistics ===");
        System.out.println("Total deliveries: " + ControlCenter.gettotalorders() + ". Succesfullorders : "+ ControlCenter.getsuccesfulorders() + ". Failed ones : " + ControlCenter.getfailedorders()  );
        for (Drone drone : controlCenter.getFleet()) {
        System.out.println(drone + " | Total distance: " + drone.getTotalDistance() + " km | Battery: " + drone.getBattery() + "%");
        }
    }
}