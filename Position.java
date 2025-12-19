public class Position {
   private double x;
   private double y;
   // Constructors
   public Position() {
       this.x=0;
       this.y=0;
   }
   
   public Position(double x,double y) {
       this.x=x;
       this.y=y;
   }
   

  //Methods
   public double distanceTo(Position other) {
	   double dx = other.x - this.x;
       double dy = other.y - this.y;
       return Math.sqrt(dx * dx + dy * dy);
   }
   
   
   public void moveTo(Position destination, double step) {
       double distance = distanceTo(destination);
       if (distance == 0 || step >= distance) {
           this.x = destination.x;
           this.y = destination.y;
           return;
       }
       double ratio = step / distance;
       this.x += ratio * (destination.x - this.x);
       this.y += ratio * (destination.y - this.y);
   }
   
   //getters
   public double getX() {
       return x;
   }

   public double getY() {
       return y;
   }
   //seters
   public void SetX(double x) {
       this.x=x;
   }

   public void SetY(double y) {
       this.y=y;
   }
   //other Methods
   public String toString() {
       return "Position(x=" + x + ", y=" + y + ")";
   }
   

   
   public boolean equals(Position other) {
	    if (other == null) return false;
	    if (this == other) return true;
	    return Double.compare(this.x, other.x) == 0 &&
	           Double.compare(this.y, other.y) == 0;
	}
   
   
}
