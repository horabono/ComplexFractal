package complexfractal.gui;

import java.util.ArrayList;

/**
 *
 * @author Horacio Bono
 */
public class DrawingParams {
	private boolean frame, center, origin, hgrid, vgrid, spec;
    private int marked;

    private static final ArrayList<DrawingListener> listeners = 
    		new ArrayList<>();

    public static void addDrawingListener(DrawingListener listener) {
        listeners.add(listener);
    }

    private static DrawingParams params = null;
    
    private DrawingParams() {
        frame = center = origin = hgrid = vgrid = spec = false;
        marked = -1;
    }
    
    public static DrawingParams get() {
        if(params == null) {
            params = new DrawingParams();
        }
        return params;
    }
    
    public void setSpec(DrawingSpec drawSpec) {
        boolean value = drawSpec.isSelected();
        boolean changed = false;
        
        switch(drawSpec) {
		case FRAME:
        	if(frame != value) {
        		frame = value;
        		changed = true;
        	} break;
		case HORIZONTAL:
        	if(hgrid != value) {
        		hgrid = value;
        		changed = true;
        	} break;
		case VERTICAL:
        	if(vgrid != value) {
        		vgrid = value;
        		changed = true;
        	} break;
		case CENTER:
        	if(center != value) {
        		center = value;
        		changed = true;
        	} break;
		case ORIGIN:
        	if(origin != value) {
        		origin = value;
        		changed = true;
        	} break;
		case SPEC:
        	if(spec != value) {
        		spec = value;
        		changed = true;
        	} break;
        }
        
    	if(changed) {
            for(DrawingListener pl : listeners) {
                pl.viewChanged();
            } 
    	}
    }

    public void setMarked(int marked) {
        if(this.marked != marked) {
            this.marked = marked;
            for(DrawingListener pl : listeners) {
                pl.viewChanged();
            } 
        } else if(this.marked >= 0) {
            this.marked = -1;
            for(DrawingListener pl : listeners) {
                pl.viewChanged();
            } 
        }
    }

	public boolean isFrame() {
		return frame;
	}

	public boolean isCenter() {
		return center;
	}

	public boolean isOrigin() {
		return origin;
	}

	public boolean isHgrid() {
		return hgrid;
	}

	public boolean isVgrid() {
		return vgrid;
	}

	public boolean isSpec() {
		return spec;
	}

    public int getMarked() {
        return marked;
    }
    
}
