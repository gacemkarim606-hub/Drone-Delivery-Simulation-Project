import java.util.ArrayList;
import java.util.List;

public abstract class Drone {

    private static int nextId = 1;

    // Attributes
    protected int id;
    protected Position position;
    protected double battery; // 0–100%
    protected String model;
    protected double speed; // km/h
    protected double capacity; // kg
    protected String status; // ("AVAILABLE", "IN DELIVERY", "RETURN TO BASE")
    protected double totalDistance; // km
    protected List<Position> positionHistory;

    // Constructor
    public Drone(Position startPosition, String model, double speed, double capacity) {
        this.id = nextId++;
        this.position = startPosition;
        this.battery = 100.0;
        this.model = model;
        this.speed = speed;
        this.capacity = capacity;
        this.status = "AVAILABLE";
        this.totalDistance = 0.0;
        this.positionHistory = new ArrayList<>();
        this.positionHistory.add(new Position(position.getX(), position.getY()));
    }
    
    //getters
    public double getBattery() { return battery; }
    public Position getPosition() { return position; }
    public String getStatus() { return status; }
    public double getTotalDistance() { return totalDistance; }
    public List<Position> getPositionHistory() { return positionHistory; }
	public double getspeed(){return speed ; }
    //abstract Methods
    public abstract double calculateConsumption(double distance);
    
    //concrete methods
    public boolean canFlyTo(Position destination) {
    	double distance = position.distanceTo(destination) * 2; // drone go and come back
        double required = calculateConsumption(distance);
        return battery >= required;
    }
    
    public void flyTo(Position destination) {
        double distance = position.distanceTo(destination);
        double consumption = calculateConsumption(distance);

        if (battery < consumption) {
            System.out.println("Not enough battery to fly!");
            return;
        }

        battery -= consumption;
        position = destination;
        totalDistance += distance;
        positionHistory.add(new Position(destination.getX(), destination.getY()));
        status = "IN DELIVERY";
    }
    
    public void recharge(double percentage) {
        battery += percentage;
        if (battery > 100) battery = 100;
        status = "AVAILABLE";
    }
    
    public String toString() {
        return model + " Drone [ID=" + id + ", Battery=" + battery + "%, Position=" + position + "]";
    }
    
    public boolean equals(Drone other) {
        if (other == null) return false;   // check null
        if (this == other) return true;    // same object
        return this.id == other.id;        // compare IDs
    }
    //extra methods for controlCenter
    public void consumeBattery(double amount) {
        battery -= amount;
        if (battery < 0) battery = 0;
    }
    
    public double getCapacity() {
        return capacity;
    }
       
}

