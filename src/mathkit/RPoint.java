package mathkit;

/**
 *
 * @author Horacio Bono
 */
public class RPoint {
    private double x;
    private double y;
    
    private static final RPoint ORIGIN = new RPoint(0, 0);

    public RPoint() {
        x = y = 0.0;
    }

    public RPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public RPoint(RPoint other) {
        x = other.x;
        y = other.y;
    }

    public RPoint(ZPoint other) {
        x = other.getX();
        y = other.getY();
    }

    public RPoint(Complex other) {
        x = other.getReal();
        y = other.getImag();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double distance(RPoint other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public boolean equals(Object other) {
        boolean eq = other instanceof RPoint;

        if(eq) {
            RPoint p = (RPoint)other;
            eq = x == p.x && y == p.y;
        }

        return eq;
    }
    
    @Override
    public String toString() {
        return x + ", " + y;
    }

    public RPoint setX(double x) {
        this.x = x;
        return this;
    }

    public RPoint setY(double y) {
        this.y = y;
        return this;
    }
    
    public RPoint set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public RPoint set(RPoint other) {
        this.x = other.x;
        this.y = other.y;
        return this;
    }
    
    public RPoint set(ZPoint other) {
        x = other.getX();
        y = other.getY();
        return this;
    }

    public RPoint set(Complex other) {
        x = other.getReal();
        y = other.getImag();
        return this;
    }
    
    public RPoint reset() {
        x = y = 0.0;
        return this;
    }
    
    public RPoint offset(double dx, double dy) {
        x += dx;
        y += dy;
        return this;
    }
    
    public static RPoint origin() {
    	return new RPoint(ORIGIN);
    }
    
    public static RPoint offset(RPoint p, double dx, double dy) {
        return new RPoint(p.x + dx, p.y + dy);
    }
    
    public static double distance(RPoint p, RPoint q) {
        double dx = p.x - q.x;
        double dy = p.y - q.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

}
