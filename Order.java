public class Order {
    private static int nextId = 1;

    // Attributes
    private int id;
    private String client;
    private Deliverable deliverable;
    private double cost;
    private String urgency; // "NORMAL" or "EXPRESS"
    private String status;  // "PENDING","IN PROGRESS","DELIVERED","FAILED"
    private String FailureReason ;
    // Constructor
    public Order(String client, Deliverable deliverable,String urgency, double cost) {
        this.id = nextId++;
        this.client = client;
        this.deliverable = deliverable;
        this.urgency = urgency;
        this.cost = cost;
        this.status = "PENDING";
    }
    
    //methods
    public String toString() {
        return "Order{id=" + id +
               ", client='" + client + '\'' +
               ", deliverable=" + deliverable +
               ", urgency=" + urgency +
               ", cost=" + cost +
               ", status=" + status + 
               (FailureReason != null ?", reason = " + FailureReason:"") + '}';
    }

    public boolean equals(Order other) {
        if (other == null) return false;
        if (this == other) return true;
        return this.id == other.id;
    }

    //extra methods for controlCenter

    public Position getDestination() {
        return deliverable.getDestination();
    }

    public boolean isExpress() {
        return "EXPRESS".equalsIgnoreCase(urgency);
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setState(String status) {
        this.status = status;
    }

    public String getState() {
        return this.status;
    }
    
    public Deliverable getDeliverable() {
        return deliverable;
    }
    
    public double getCost() {
        return cost;
    }
    public void fail(String reason){
        this.status = OrderState.FAILED ; 
        this.FailureReason = reason ; 
    }

}
