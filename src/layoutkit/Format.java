package layoutkit;

import java.text.DecimalFormat;

import mathkit.Complex;
import mathkit.RField;
import mathkit.RPoint;

/**
 *
 * @author Horacio Bono
 */
public final class Format {
    public static final DecimalFormat REAL_FORMAT = 
    		new DecimalFormat("##0.######");

    public static final String stringReal(RPoint p) {
        return string(p.getX(), p.getY());
    }

    public static final String stringReal(RField p) {
        return string(p.getWidth(), p.getHeight());
    }

    public static final String stringReal(Complex p) {
        return string(p.getReal(), p.getImag());
    }
    
    private static String string(double p, double q) {
        return REAL_FORMAT.format(p) + ", " + REAL_FORMAT.format(q);
    }
    
    public static String complexFormat(Complex z) {
    	return stringReal(z) + "i";
    }
    
    public static String complexFormat(RPoint p) {
    	return stringReal(p) + "i";
    }

}
