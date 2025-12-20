import java.util.ArrayList;
import java.util.List;

public class ControlCenter {

    // Attributes
    private List<Drone> fleet;
    private List<Order> pendingOrders;
    private List<Order> processedOrders;
    private Position base;
    private Map map;

    // Global statistics
    private static int numberOfDeliveries = 0;
    private static double totalDistance = 0.0;
    private static double energyConsumed = 0.0;

    // Constructor
    public ControlCenter(Position base, Map map) {
        this.base = base;
        this.map = map;
        this.fleet = new ArrayList<>();
        this.pendingOrders = new ArrayList<>();
        this.processedOrders = new ArrayList<>();
    }

    // Add a drone to the fleet
    public void addDrone(Drone drone) {
        fleet.add(drone);
    }

    // Find a suitable drone for an order
    public Drone findDroneForOrder(Order order) {
        for (Drone drone : fleet) {
            if ("AVAILABLE".equals(drone.getStatus())
                && drone.getCapacity() >= order.getDeliverable().getWeight()
                && map.isAllowed(order.getDestination())
                && drone.canFlyTo(order.getDestination())) {

                return drone;
            }
        }
        return null;
    }

    // Assign an order to a drone
    public boolean assignOrder(Order order) {
        Drone drone = findDroneForOrder(order);

        // If no drone is available, add to pending orders
        if (drone == null) {
            pendingOrders.add(order);
            order.setState("PENDING");
            return false;
        }

        // Calculate round-trip distance and energy consumption
        double distance = base.distanceTo(order.getDestination()) * 2;
        double consumption = drone.calculateConsumption(distance);
        drone.consumeBattery(consumption);

        // Calculate delivery cost and update order
        double deliveryCost = calculateDeliveryCost(order, drone);
        order.setCost(deliveryCost);

        // Update statuses
        order.setState("IN PROGRESS");
        drone.status = "IN DELIVERY";

        // Finish delivery
        order.setState("DELIVERED");
        drone.status = "RETURN TO BASE";
        drone.status = "AVAILABLE";

        // Add to processed orders
        processedOrders.add(order);

        // Update global statistics
        numberOfDeliveries++;
        totalDistance += distance;
        energyConsumed += consumption;

        return true;
    }

    // Calculate the delivery cost of an order
    public double calculateDeliveryCost(Order order, Drone drone) {
        double initialPrice = order.getCost();

        double distance = base.distanceTo(order.getDestination()) * 2;
        double consumption = drone.calculateConsumption(distance);

        // Operation cost formula
        double operationCost = (distance * 0.1) + (consumption * 0.02) + 20;

        // Insurance formula
        double insurance = Math.max(initialPrice * 0.02, 10);
        if (order.isExpress()) {
            insurance += 20;
        }

        return operationCost + insurance;
    }

    // Statistics getters
    public static int getNumberOfDeliveries() { return numberOfDeliveries; }
    public static double getTotalDistance() { return totalDistance; }
    public static double getEnergyConsumed() { return energyConsumed; }

    // Lists getters
    public List<Order> getPendingOrders() { return pendingOrders; }
    public List<Order> getProcessedOrders() { return processedOrders; }
    public List<Drone> getFleet() { return fleet; }
}