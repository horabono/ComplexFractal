package complexfractal.gui;

import java.awt.Color;

/**
 *
 * @author Horacio Bono
 */
public final class PaletteGenerator {
    public Color[] colors;
    
    private Palette palette;
    private boolean up;
    private boolean greyed;
    private boolean alternated;
    
    private short[] track;
    
    public PaletteGenerator() {
        PaletteParams params = PaletteParams.get();
        
        alternated = params.alternate;
        palette = params.palette;
        up = params.up;
        
        int count = params.count;
        colors = new Color[count+1];
        
        colors[count--] = Color.BLACK;
        colors[count] = Color.WHITE;
        
        track = new short[6];
        
        short c = (short)(count / 6);
        for(int i = 0; i < 6; i++) {
        	track[i] = c;
        }
        
        c = (short)(count % 6);
        for(int i = c - 1; i >= 0; i--) {
        	track[i]++;
        }

        setGreyed(params.greyed);
    }
    
    public void setGreyed(boolean greyed) {
        this.greyed = greyed;
        
        if(greyed) {
            createGreyed();
        } else {
            setPalette();
        }
        
        if(alternated) {
        	alternateColors();
        }
    }
    
    public void setUp(boolean up) {
    	this.up = up;
        
        if(greyed) {
            createGreyed();
        } else {
            setPalette();
        }
        
        if(alternated) {
        	alternateColors();
        }
    }
    
    public void setAlternate(boolean alternate) {
    	if(this.alternated != alternate) {
        	this.alternated = alternate;
        	alternateColors();
    	}
    }
    
    public void setScale(Palette palette) {
    	this.palette = palette;
    
        setPalette();
    }
    
    private short setTrack(Palette palette, boolean up, short count, short c) {
        float r, g, b;

        r = palette.red;
        g = palette.green;
        b = palette.blue;
        
        switch(palette.inc(up)) {
        case 1:
            for(int i = 0; i < count; i++) {
                float f = (float)i / count;
                colors[c++] = new Color(r+f, g, b);
            }
            break;
        case 2:
            for(int i = 0; i < count; i++) {
                float f = (float)i / count;
                colors[c++] = new Color(r, g+f, b);
            }
            break;
        case 3:
            for(int i = 0; i < count; i++) {
                float f = (float)i / count;
                colors[c++] = new Color(r, g, b+f);
            }
            break;
        case -1:
            for(int i = 0; i < count; i++) {
                float f = (float)i / count;
                colors[c++] = new Color(r-f, g, b);
            }
            break;
        case -2:
            for(int i = 0; i < count; i++) {
                float f = (float)i / count;
                colors[c++] = new Color(r, g-f, b);
            }
            break;
        case -3:
            for(int i = 0; i < count; i++) {
                float f = (float)i / count;
                colors[c++] = new Color(r, g, b-f);
            }
            break;
        }
    	return c;
    }
    
    private void setPalette() {
        Palette sc = palette;
        short c = 0;

        for(int i = 0; i < 6; i++) {
        	c = setTrack(sc, up, track[i], c);
        	sc = sc.next(up);
        }
    }
    
    private void createGreyed() {
        int count = colors.length - 2;
        float d = 0.8f / count;
        float f;
        int c;
        
        if(up) {
            for(f = 0.1f, c = 0; c < count; c++, f += d) {
                colors[c] = new Color(f, f, f);
            }
        } else {
            for(f = 0.9f, c = 0; c < count; c++, f -= d) {
                colors[c] = new Color(f, f, f);
            }
        }
    }
    
    private void alternateColors() {
        int i, j;
        Color aux;
        
        i = 1;
        j = colors.length - 3;
        if((j & 1) == 0) {
            j--;
        }
        
        while(i < j) {
            aux = colors[i];
            colors[i] = colors[j];
            colors[j] = aux;
            i += 2;
            j -= 2;
        }
    }

}
