public class DeliveryItem implements Deliverable {

    private Position destination;
    private double weight;

    // Constructor
    public DeliveryItem(Position destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public Position getDestination() {
        return destination;
    }
}
