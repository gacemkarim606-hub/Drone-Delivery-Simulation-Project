public class NoFlyZone {
	// Attributes
    private Position center;
    private double radius; 

    // Constructor
    public NoFlyZone(Position center, double radius) {
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

    // Methods
    public boolean contains(Position p) {
        return center.distanceTo(p) <= radius;
    }

    public String toString() {
        return "NoFlyZone(center=" + center + ", radius=" + radius + ")";
    }
}

