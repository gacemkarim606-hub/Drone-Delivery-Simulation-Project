import java.util.List;

public class Simulator {

    private ControlCenter controlCenter;

    public Simulator(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }

    public void runSimulation() {
        int totalMinutes = 480; // 8 hours

        for (int minute = 1; minute <= totalMinutes; minute++) {

            // 1. Assign pending orders
            List<Order> pendingOrders = controlCenter.getPendingOrders();
            for (Order order : pendingOrders.toArray(new Order[0])) { // iterate safely
                boolean assigned = controlCenter.assignOrder(order);
                if (assigned) {
                    pendingOrders.remove(order); // remove from pending if assigned
                }
            }
            // 2. Move drones that are in delivery
            for (Drone drone : controlCenter.getFleet()) {
                if ("IN_DELIVERY".equals(drone.getStatus())) {
                    // Gradually move drone towards destination
                    Order order = findOrderByDrone(drone);
                    if (order != null) {
                        moveDroneStep(drone, order);
                    }
                }
            }
        }
        // 3. Print final statistics
        printStatistics();
    }
    // Find the order currently assigned to a drone
    private Order findOrderByDrone(Drone drone) {
        for (Order order : controlCenter.getProcessedOrders()) {
            // If drone is still delivering, order might be in IN PROGRESS
            if ("IN_PROGRESS".equals(order.getState()) || "DELIVERED".equals(order.getState())) {
                // You may need to store assigned drone inside Order in a real implementation
                // Here, assume one drone per order mapping
                return order;
            }
        }
        return null;
    }

    // Simplified step movement
    private void moveDroneStep(Drone drone, Order order) {
        try {     
        Position current = drone.getPosition();
        Position destination = order.getDestination();

        // Move one step (you can adjust step size, e.g., 1 km per minute)
        double step = 1.0; // km per minute
        double distance = current.distanceTo(destination);

        if (distance <= step) {
            // Drone reached destination
            drone.flyTo(destination); // move final step
            order.setState("DELIVERED");
            drone.status = "AVAILABLE";
        } else {
            // Move towards destination
            double ratio = step / distance;
            double newX = current.getX() + (destination.getX() - current.getX()) * ratio;
            double newY = current.getY() + (destination.getY() - current.getY()) * ratio;
            Position newPos = new Position(newX, newY);

            // Update drone
            drone.flyTo(newPos); // consumes battery automatically
        }
    }catch(Exception e){
        order.fail("battery failure during flight") ;
        drone.status = "RETURN_TO_BASE"  ; 
    }
    }
        private void printStatistics() {
        System.out.println("Simulation finished!");
        System.out.println("Total deliveries: " + ControlCenter.gettotalorders());
        for (Drone drone : controlCenter.getFleet()) {
            System.out.println(drone + " | Total distance: " + drone.getTotalDistance() + " km | Battery: " + drone.getBattery() + "%");
        }
    }
}