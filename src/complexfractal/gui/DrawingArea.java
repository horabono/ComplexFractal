package complexfractal.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import complexfractal.FractalGenerator;
import complexfractal.FractalParams;
import mathkit.Complex;
import mathkit.ZField;

/**
 *
 * @author Horacio Bono
 */
public class DrawingArea extends JPanel 
	implements MouseListener, FractalListener, DrawingListener, PaletteListener {
	
	private static final long serialVersionUID = 1L;

    private static final ArrayList<AreaListener> listeners = new ArrayList<>();
    public static void addAreaListener(AreaListener listener) {
    	listeners.add(listener);
    }

    private final ZField size;
    private FieldParams params;
    private FractalGenerator generator;
    
    public DrawingArea() {
        setOpaque(true);
        this.setBackground(Color.WHITE);

        size = new ZField();
        addMouseListener(this);
        FractalParams.addFractalListener(this);
        DrawingParams.addDrawingListener(this);
        PaletteParams.addPaletteListener(this);
    }
    
    // Called by ControlWindow
    public void setSize() {
        size.set(getWidth(), getHeight()).subtract(90, 50);
        params = new FieldParams(size);
        generator = new FractalGenerator(params);
        createFractal();
    }
    
    // Called by FullWindow
    public void setSize(FieldParams params) {
        size.set(getWidth(), getHeight()).subtract(75, 50);
        this.params = new FieldParams(params, size);
        generator = new FractalGenerator(this.params);
        createFractal();
    }
	
	private void createFractal() {
        generator.createSet(params);
        repaint();
	}
    
    public FieldParams getParams() {
    	return params;
    }

	@Override
    public void mouseClicked(MouseEvent e) { 
        Complex z = new Complex(params.area.minXY());
        double x = e.getX() * params.step;
        double y = (size.getHeight() - e.getY()) * params.step;
        Complex c = new Complex(x, y).add(z);
        
        for(AreaListener al : listeners) {
        	al.centerChanged(c);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

	@Override
	public void setChanged() {
        generator.createSet(params);
        repaint();
	}

	@Override
	public void constChanged() {
        generator.createSet(params);
        repaint();
	}

	@Override
	public void centerChanged(Complex center) {
		params.setCenter(center);
        generator.createSet(params);
        repaint();
	}

	@Override
	public void iterChanged() {
        generator.createSet(params);
        repaint();
	}

	@Override
	public void zoomChanged(int zoom) {
		params.setZoom(zoom);
        generator.createSet(params);
        repaint();
	}

	@Override
	public void viewChanged() {
        repaint();
	}

    @Override
    public void paintComponent(Graphics g) { 
    	if(generator != null) {
            super.paintComponent(g);
            FractalDrawer drawer = new FractalDrawer(g, generator.getMatrix(), params);
            drawer.draw();
    	}
    }

	@Override
	public void paletteChanged() {
        repaint();
	}
    
}
