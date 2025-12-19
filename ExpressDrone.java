public class ExpressDrone extends Drone {

    public ExpressDrone(Position startPosition) {
        super(startPosition, "Express", 45, 1.0);
    }

    public double calculateConsumption(double distance) {
        return distance * 4.0; // 4% per km
    }
}

