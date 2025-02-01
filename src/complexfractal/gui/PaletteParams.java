package complexfractal.gui;

import java.awt.Color;
import java.util.ArrayList;

import complexfractal.FractalParams;

/**
 *
 * @author Horacio Bono
 */
public class PaletteParams {
    public boolean up, greyed, alternate;
    public short count;
    public Palette palette;
    private PaletteGenerator generator;

    private static PaletteParams params = null;
	 
    private static final 
    	ArrayList<PaletteListener> listeners = new ArrayList<>();

    public static void addPaletteListener(PaletteListener listener) {
        listeners.add(listener);
    }
    
    public static PaletteParams get() {
        if(params == null) {
            params = new PaletteParams();
            params.setCount(FractalParams.get().getIter());
        }
        return params;
    }

	private PaletteParams() {
        up = greyed = alternate = false;
        palette = Palette.MAGENTA;
        count = 0;
	}
    
    public void setSpec(PaletteSpec spec) {
        boolean value = spec.isSelected();
        boolean changed = false;

        switch(spec) {
        case UP:
        	if(up != value) {
        		up = value;
        		generator.setUp(up);
        		changed = true;
        	} break;
		case GREYED:
        	if(greyed != value) {
        		greyed = value;
        		generator.setGreyed(greyed);
        		changed = true;
        	} break;
		case ALTERNATE:
        	if(alternate != value) {
        		alternate = value;
        		generator.setAlternate(alternate);
        		changed = true;
        	} break;
        }
        
        if(changed) {
            for(PaletteListener pl : listeners) {
                pl.paletteChanged();
            } 
        }
        
    }
    
    public void setCount(short count) {
    	if(this.count != count) {
    		this.count = count;
    		generator = new PaletteGenerator();
            for(PaletteListener pl : listeners) {
                pl.paletteChanged();
            } 
    	}
    }
    
    public void setPalette(Palette palette) {
    	if(this.palette != palette) {
    		this.palette = palette;
            generator.setScale(palette);
            
            for(PaletteListener pl : listeners) {
                pl.paletteChanged();
            } 
    	}
    }

	public Color[] getColors() {
		return generator.colors;
	}
	
	public int getCount() {
		return count;
	}

    public boolean isGreyed() {
        return greyed;
    }
    
    @Override
    public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Palette: " + palette);
		sb.append(" | Greyed: " + greyed);
		sb.append(" | Alternate: " + alternate);
		sb.append(" | Palette Up: " + up);
		
    	return sb.toString();
    }
}
