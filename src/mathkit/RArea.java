package mathkit;

/**
 *
 * @author Horacio Bono
 */
public class RArea {
    private final RField field;
    private final RPoint center;
    
    public RArea() {
        field = new RField();
        center = new RPoint();
    }

    public RArea(double x, double y, double width, double height) {
        field = new RField(width, height);
        center = new RPoint(x, y);
    }

    public RArea(RPoint center, RField field) {
        this.field = new RField(field);
        this.center = new RPoint(center);
    }

    public RArea(RArea other) {
        field = new RField(other.field);
        center = new RPoint(other.center);
    }

    public RArea(RField field) {
        this.field = new RField(field);
        this.center = new RPoint();
    }

    public RPoint getCenter() {
        return center;
    }

    public RField getField() {
        return field;
    }
    
    public double getWidth() {
        return field.getWidth();
    }
    
    public double getHeight() {
        return field.getHeight();
    }

    public RArea set(RArea other) {
        center.set(other.center);
        field.set(other.field);
        return this;
    }

    public RArea set(double x, double y, double width, double height) {
        center.set(x, y);
        field.set(width, height);
        return this;
    }

    public RArea set(RPoint center, RField field) {
        this.center.set(center);
        this.field.set(field);
        return this;
    }

    public RArea setCenter(RPoint center) {
        this.center.set(center);
        return this;
    }

    public RArea setCenter(double x, double y) {
        center.set(x, y);
        return this;
    }

    public RArea setField(RField field) {
        this.field.set(field);
        return this;
    }

    public RArea setField(double width, double height) {
        field.set(width, height);
        return this;
    }
    
    public RArea factor(double factor) {
    	field.factor(factor);
        return this;
    }
    
    public double minX() {
        return center.getX() - field.getWidth() / 2;
    }
    
    public double minY() {
        return center.getY() - field.getHeight() / 2;
    }
    
    public double maxX() {
        return center.getX() + field.getWidth() / 2;
    }
    
    public double maxY() {
        return center.getY() + field.getHeight() / 2;
    }
    
    public RPoint minXY() {
        return new RPoint(minX(), minY());
    }
    
    public RPoint maxXY() {
        return new RPoint(maxX(), maxY());
    }
    
    @Override
    public boolean equals(Object other) {
        boolean eq = other instanceof RArea;

        if(eq) {
        	RArea f = (RArea)other;
            eq = center.equals(f.center) && field.equals(f.field);
        }

        return eq;
    }
    
    @Override
    public String toString() {
    	return field + " " + center;
    }

}
