public class StandardDrone extends Drone {
	    public StandardDrone(Position startPosition, String model, double speed, double capacity) {
	    super(startPosition, model, speed, capacity);
	    }

	    public double calculateConsumption(double distance) {
	    return distance * 1.0; // 1% battery per km
	    }
	}
