public class StandardPackage implements Deliverable {

    private double weight;        // kg
    private Position destination;

    public StandardPackage(double weight, Position destination) {
        this.weight = weight;
        this.destination = destination;
    }


    public double getWeight() {
        return weight;
    }


    public Position getDestination() {
        return destination;
    }

    public String toString() {
        return "StandardPackage(weight=" + weight +", destination=" + destination + ")";
    }
}


