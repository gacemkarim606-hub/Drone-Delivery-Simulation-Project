public class HeavyDrone extends Drone {

    public HeavyDrone(Position startPosition) {
        super(startPosition, "Heavy", 20, 3.0);
    }

    public double calculateConsumption(double distance) {
        return distance * 5.0; // 5% per km
    }

    public void flyTo(Position destination) {
        if (battery < 20) speed = 16; // Speed reduced to 16 km/h if battery < 20%
        super.flyTo(destination);
        speed = 20; // Reset speed after flight
    }
}
