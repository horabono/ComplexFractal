package mathkit;

/**
 *
 * @author Horacio Bono
 */
public class RField  {
    private double width;
    private double height;

    public RField() {
        width = height = 0.0;
    }

    public RField(double size) {
        width = height = Math.abs(size);
    }

    public RField(double width, double height) {
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }

    public RField(RField other) {
        width = other.width;
        height = other.height;
    }

    public RField(ZField other) {
        width = other.getWidth();
        height = other.getHeight();
    }

    public RField set(double width, double height) {
        this.width = Math.abs(width);
        this.height = Math.abs(height);
        return this;
    }

    public RField set(RField other) {
        width = other.width;
        height = other.height;
        return this;
    }

    public RField set(ZField other) {
        width = other.getWidth();
        height = other.getHeight();
        return this;
    }
    
    public RField reset() {
        width = height = 0.0;
        return this;
    }

    public RField add(double width, double height) {
        this.width = Math.abs(this.width + width);
        this.height = Math.abs(this.height + height);
        return this;
    }

    public RField add(double value) {
        value = Math.abs(value);
        this.width += value;
        this.height += value;
        return this;
    }

    public RField subtract(double width, double height) {
        this.width = Math.abs(this.width - width);
        this.height = Math.abs(this.height - height);
        return this;
    }
    
    public RField factor(double factor) {
        factor = Math.abs(factor);
        width *= factor;
        height *= factor;
        return this;
    }
    
    public RField divide(double factor) {
        factor = Math.abs(factor);
        width /= factor;
        height /= factor;
        return this;
    }

    public RField setWidth(double width) {
        this.width = Math.abs(width);
    	return this;
    }

    public RField setHeight(double height) {
        this.height = Math.abs(height);
    	return this;
    }

    public RField setWidthRatio(double ratio) {
        width = Math.abs(height / ratio);
    	return this;
    }

    public RField setHeightRatio(double ratio) {
        height = Math.abs(width * ratio);
    	return this;
    }
    
    public RField setRatio(double ratio) {
    	double area = width * height;
    	ratio = Math.abs(ratio);
    	width = Math.sqrt(area / ratio);
    	height = Math.sqrt(area * ratio);
    	return this;
    }
    
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    
    public double getRatio() {
    	return height / width;
    }
    
    public static RField divide(RField f, double factor) {
        return new RField(f).divide(factor);
    }
    
    public static RField factor(RField f, double factor) {
        return new RField(f).factor(factor);
    }
    
    public static RField factor(ZField f, double factor) {
        return new RField(f).factor(factor);
    }
    
    @Override
    public boolean equals(Object other) {
        boolean eq = other instanceof RField;

        if(eq) {
            RField f = (RField)other;
            eq = width == f.width && height == f.height;
        }

        return eq;
    }
    
    @Override
    public String toString() {
        return width + " x " + height;
    }
}
