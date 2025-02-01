package mathkit;

/**
 *
 * @author Horacio Bono
 */
public class ZPoint{
    private int x;
    private int y;

    public ZPoint() {
        x = y = 0;
    }

    public ZPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ZPoint(ZPoint other) {
        x = other.x;
        y = other.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ZPoint setX(int x) {
        this.x = x;
        return this;
    }

    public ZPoint setY(int y) {
        this.y = y;
        return this;
    }
    
    public ZPoint set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public ZPoint set(ZPoint other) {
        this.x = other.x;
        this.y = other.y;
        return this;
    }
    
    public ZPoint reset() {
        x = y = 0;
        return this;
    }
    
    public ZPoint offset(int dx, int dy) {
        x += dx;
        y += dy;
        return this;
    }

    public ZPoint subtract(ZPoint other) {
        x -= other.x;
        y -= other.y;
        return this;
    }
    
    public ZPoint subtract(int dx, int dy) {
        x -= dx;
        y -= dy;
        return this;
    }
    
    public double distance(ZPoint other) {
        int dx = x - other.x;
        int dy = y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public static ZPoint offset(ZPoint p, int dx, int dy) {
        return new ZPoint(p.x + dx, p.y + dy);
    }
    
    @Override
    public boolean equals(Object other) {
        boolean eq = other instanceof ZPoint;

        if(eq) {
            ZPoint p = (ZPoint)other;
            eq = x == p.x && y == p.y;
        }

        return eq;
    }
    
}
