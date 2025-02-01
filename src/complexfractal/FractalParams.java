package complexfractal;

import java.util.ArrayList;

import complexfractal.gui.FractalListener;
import complexfractal.gui.FractalSet;
import layoutkit.Format;
import mathkit.Complex;
import mathkit.RField;

/**
 *
 * @author Horacio Bono
 */
public final class FractalParams {

	public static final short MIN_ITERATIONS = 1;
	public static final short MAX_ITERATIONS = 1000; //752; // 600; // 127;
	public static final int MIN_ZOOM = 5;
    public static final short DEFAULT_ITERATIONS = 13;
    public static final int DEFAULT_ZOOM = 100;
    public static final RField DEFAULT_FIELD = new RField(4, 3);
	public static final double DEFAULT_RATIO = DEFAULT_FIELD.getRatio();
	
	private static FractalParams params = null;
	
	public static FractalParams get() {
		if(params == null) {
			params = new FractalParams();
		}
		return params;
	}
	
	private static ArrayList<FractalListener> listeners = new ArrayList<>();
	public static void addFractalListener(FractalListener listener) {
		listeners.add(listener);
	}
	
	private final Complex constant;
	private final Complex center;
	private FractalSet set;
	private short iter;
	private int zoom;
	
	public Complex getConstant() {
		return constant;
	}

	public Complex getCenter() {
		return center;
	}

	public FractalSet getSet() {
		return set;
	}

	public short getIter() {
		return iter;
	}

	public int getZoom() {
		return zoom;
	}

	private FractalParams() {
        constant = new Complex(0, 0);
        center = new Complex(0, 0);
        set = FractalSet.MANDELBROT;
        iter = DEFAULT_ITERATIONS;
        zoom = DEFAULT_ZOOM;
	}
    
    public void setConstant(Complex constant) {
    	if(!this.constant.equals(constant)) {
    		this.constant.set(constant);
    		for(FractalListener listener : listeners) {
    			listener.constChanged();
    		}
    	}
    }
    
    public void setCenter(Complex center) {
    	if(!this.center.equals(center)) {
    		this.center.set(center);
    		for(FractalListener listener : listeners) {
    			listener.centerChanged(center);
    		}
    	}
    }

    public void setSet(FractalSet set) {
    	if(this.set != set) {
    		this.set = set;
    		for(FractalListener listener : listeners) {
    			listener.setChanged();
    		}
    	}
    }
    
    public void setIter(short iter) {
    	if(this.iter != iter) {
    		this.iter = iter;
    		for(FractalListener listener : listeners) {
    			listener.iterChanged();
    		}
    	}
    }
    
    public void setZoom(int zoom) {
    	if(this.zoom != zoom) {
    		this.zoom = zoom;
    		for(FractalListener listener : listeners) {
    			listener.zoomChanged(zoom);
    		}
    	}
    }
        
    @Override
    public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Set: " + set);
		sb.append(" | Const: " + Format.complexFormat(constant));
		sb.append(" | Center: " + Format.complexFormat(center));
		sb.append(" | Iter: " + iter);
		sb.append(" | Zoom: " + zoom);
		
    	return sb.toString();
    }
    
}
