package complexfractal;

import complexfractal.gui.FieldParams;
import mathkit.Complex;

/**
 *
 * @author Horacio Bono
 */
public final class ComplexScanner {
    private final double step;
    private final int height, width;
    private final Complex start;

    private Complex value;
    private int row, col;
    
    public ComplexScanner(FieldParams params) {
    	step = params.step;
        height = params.size.getHeight();
        width = params.size.getWidth();
        start =  new Complex(params.area.minXY());
        
        value = new Complex(start);
        row = col = 0;
    }
    
    public void reset() {
        value.set(start);
        row = col = 0;
    }
    
    public Complex getValue() {
        Complex ret = null;
        
        if(row < height) {
        	ret = new Complex(value);

        	if(++col < width) {
            	value.addReal(step);
        	} else {
        		value.setReal(start.getReal());
            	value.addImag(step);
            	col = 0;
            	row++;
        	}
        } else {
        	System.out.println("ComplexScanner.getValue row = " + row + " height = " + height + " col = " + col + " width = " + width);
        }

        return ret;
    }
    
}
