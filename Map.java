
import java.util.ArrayList;
import java.util.List;

public class Map {
	// Attributes
    private List<DeliveryZone> deliveryZones;
    private List<NoFlyZone> noFlyZones;

    // Constructor
    public Map() {
        deliveryZones = new ArrayList<>();
        noFlyZones = new ArrayList<>();
    }

    // Getters
    public List<DeliveryZone> getDeliveryZones() {
        return deliveryZones;
    }

    public List<NoFlyZone> getNoFlyZones() {
        return noFlyZones;
    }

    // Add zones
    public void addDeliveryZone(DeliveryZone dz) {
        deliveryZones.add(dz);
    }

    public void addNoFlyZone(NoFlyZone nfz) {
        noFlyZones.add(nfz);
    }

    // true if p is NOT inside any no-fly zone
    public boolean isAllowed(Position p) {
        for (NoFlyZone zone : noFlyZones) {
            if (zone.contains(p)) {
                return false;
            }
        }
        return true;
    }

    // true if p is inside at least one no-fly zone
    public boolean isForbidden(Position p) {
        for (NoFlyZone zone : noFlyZones) {
            if (zone.contains(p)) {
                return true;
            }
        }
        return false;
    }
}
