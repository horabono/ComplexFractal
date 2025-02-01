package complexfractal.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import layoutkit.Layout;

/**
 *
 * @author Horacio Bono
 */
public class PaletteArea extends JPanel 
		implements PaletteListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private Rectangle paletteArea;

    private static final int MIN_HEIGHT = 25;
    private static final int MAX_HEIGHT = 50;
    private static final int MIN_WIDTH = 30;
    private static final int MAX_WIDTH = 75;
    private int rows, cols;
    private int step;
    
    public PaletteArea() {
    	PaletteParams.addPaletteListener(this);
    }

    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g) {
        paletteArea = new Rectangle( 
            Layout.HALF_MARGIN, 
            Layout.SINGLE_MARGIN,
            getWidth() - Layout.HALF_MARGIN, 
            getHeight() - Layout.DOUBLE_MARGIN); 
        
        Color[] palette = PaletteParams.get().getColors();

        setPaletteArea(palette.length);
        
        int w = paletteArea.width / cols;
        int h = paletteArea.height / rows;
        
        Color textColor = PaletteParams.get().isGreyed()? Color.YELLOW: Color.BLACK;
        
        int x = paletteArea.x;
        int y = paletteArea.y;
        int paletteIndex = palette.length - 1;
        int i;
        
        for(i = 0; i < 2; i++) {
            g.setColor(palette[paletteIndex]);
            g.fillRect(x, y, w, h);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, w, h);
            g.setColor(textColor);
            g.drawString(String.format("%d", paletteIndex), x + Layout.HALF_MARGIN, y + Layout.SINGLE_MARGIN);
            y += h;
            paletteIndex--;
        }
        
        for(int j = 0; paletteIndex >= 0 && j < cols; j++) {
            while(paletteIndex >= 0 && i++ < rows) {
                g.setColor(palette[paletteIndex]);
                g.fillRect(x, y, w, h);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, w, h);
                g.setColor(textColor);
                g.drawString(String.format("%d", paletteIndex), x + Layout.HALF_MARGIN, y + Layout.SINGLE_MARGIN);
                y += h;
            	paletteIndex -= step;
            }
            x += w;
            y = paletteArea.y;
            i = 0;
        }
    }
    
    private void setPaletteArea(int paletteLength) {
        int ph = paletteArea.height;
        int pw = paletteArea.width;
        int w, h;
        
        step = 1;
        int cells = paletteLength;
    	int maxCells = (pw / MIN_WIDTH) * (ph / MIN_HEIGHT);
        
        while(cells > maxCells) {
        	step++;
        	cells = (paletteLength - 2) / step + 2;
        }
        
        cols = 0;
        do {
            cols++;
            rows = (cells + cols - 1) / cols;
            h = ph / rows;
        } while(h < MIN_HEIGHT);
        w = (pw - Layout.DOUBLE_MARGIN) / cols;

        if(w > MAX_WIDTH) {
            w = MAX_WIDTH;
        }

        if(h > MAX_HEIGHT) {
            h = MAX_HEIGHT;
        }

        paletteArea.width = w * cols;
        paletteArea.height = h * rows;
    }
    
    private int getMarkedColor(Point p) {
        int marked;
        
        if(paletteArea.contains(p)) {
            int col = ((p.x - paletteArea.x) * cols) / paletteArea.width;
            int row = ((p.y - paletteArea.y) * rows) / paletteArea.height;
            marked = PaletteParams.get().getCount() - col * rows - row;
        } else {
            marked = 0;
        }
        
        return --marked;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int marked = getMarkedColor(e.getPoint());
        
        if(marked >= 0) {
        	DrawingParams.get().setMarked(marked);
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
	public void paletteChanged() {
        repaint();
	}

}
