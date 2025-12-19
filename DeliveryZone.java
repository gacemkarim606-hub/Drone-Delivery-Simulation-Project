public class DeliveryZone {

    private Position center;
    private double radius; 

    // Constructor
    public DeliveryZone(Position center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    // Getters
    public Position getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    // Setters
    public void setCenter(Position center) {
        this.center = center;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // methods
    public boolean contains(Position p) {
        return center.distanceTo(p) <= radius; //how much far p from the center if its in the zone or note
    }

    public String toString() {
        return "DeliveryZone(center=" + center + ", radius=" + radius + ")";
    }
	
}

