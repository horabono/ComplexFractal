package mathkit;

/**
 *
 * @author Horacio Bono
 */
public class ZField {
    private int width;
    private int height;

    public ZField() {
        width = height = 0;
    }

    public ZField(int size) {
        width = height = Math.abs(size);
    }

    public ZField(int width, int height) {
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }

    public ZField(ZField other) {
        width = other.width;
        height = other.height;
    }

    public ZField set(int width, int height) {
        this.width = Math.abs(width);
        this.height = Math.abs(height);
        return this;
    }

    public ZField set(ZField other) {
        width = other.width;
        height = other.height;
        return this;
    }
    
    public ZField reset() {
        width = height = 0;
        return this;
    }

    public ZField add(int width, int height) {
        this.width = Math.abs(this.width + width);
        this.height = Math.abs(this.height + height);
        return this;
    }

    public ZField subtract(int width, int height) {
        this.width = Math.abs(this.width - width);
        this.height = Math.abs(this.height - height);
        return this;
    }
    
    public ZField factor(double factor) {
        factor = Math.abs(factor);
        width *= factor;
        height *= factor;
        return this;
    }
    
    public ZField divide(double factor) {
        factor = Math.abs(factor);
        width /= factor;
        height /= factor;
        return this;
    }

    public ZField setWidth(int width) {
        this.width = Math.abs(width);
    	return this;
    }

    public ZField setHeight(int height) {
        this.height = Math.abs(height);
    	return this;
    }

    public ZField setWidthRatio(double ratio) {
        width = (int)Math.abs(height / ratio);
    	return this;
    }

    public ZField setHeightRatio(double ratio) {
        height = (int)Math.abs(width * ratio);
    	return this;
    }
    
    public ZField setRatio(double ratio) {
    	double area = width * height;
    	ratio = Math.abs(ratio);
    	width = (int)Math.sqrt(area / ratio);
    	height = (int)Math.sqrt(area * ratio);
    	return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public double getRatio() {
    	return (double)height / width;
    }
    
    public static ZField divide(ZField f, double factor) {
        return new ZField(f).divide(factor);
    }
    
    public static ZField factor(ZField f, double factor) {
        return new ZField(f).factor(factor);
    }
    
    @Override
    public boolean equals(Object other) {
        boolean eq = other instanceof ZField;

        if(eq) {
            ZField f = (ZField)other;
            eq = width == f.width && height == f.height;
        }

        return eq;
    }
    
    @Override
    public String toString() {
        return width + " x " + height;
    }
}
