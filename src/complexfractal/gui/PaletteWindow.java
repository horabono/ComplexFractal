package complexfractal.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import layoutkit.Layout;

public class PaletteWindow extends JFrame
	implements PaletteListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Rectangle paletteArea;
	
	private static final int MAX_W_HEIGHT = 625;
	private static final int MIN_HEIGHT = 25;
	private static final int MAX_ROWS = MAX_W_HEIGHT / MIN_HEIGHT;
	
	private static final int MAX_W_WIDTH = 896;
	private static final int MIN_WIDTH = 32;
	private static final int MAX_COLS = MAX_W_WIDTH / MIN_WIDTH;
	
	private static final int MAX_CELLS = MAX_COLS * MAX_ROWS;

	private static final int MIN_W_WIDTH = 225;
	private static final int MAX_HEIGHT = 50;
	private static final int MAX_WIDTH = 75;

	private int colorCount;
	private int colWidth, rowHeight;
	private int rows, cols;
	private int cells;
	private int step;

	private JPanel contentPane;

	public PaletteWindow() {
		setTitle("Palette");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		PaletteParams.addPaletteListener(this);
        addMouseListener(this);

		setArea();
		setVisible(true);
	}
	
	private void setArea() {
		colorCount = PaletteParams.get().getCount();
        
        cells = colorCount;
        step = 1;
        
        while(cells > MAX_CELLS) {
        	step++;
        	cells = (colorCount - 2) / step + 2;
        }
		
		cols = (cells + MAX_ROWS - 1) / MAX_ROWS;
		rows = cells / cols;
		
		if(cells % MAX_ROWS > 0) {
			rows++;
		}
		
		rowHeight = MAX_W_HEIGHT / rows;
		if(rowHeight > MAX_HEIGHT) {
			rowHeight = MAX_HEIGHT;
		}
		
		colWidth = MAX_W_WIDTH / cols;
		if(colWidth > MAX_WIDTH) {
			colWidth = MAX_WIDTH;
		}
		
        paletteArea = new Rectangle( 
    		Layout.DOUBLE_MARGIN,
    		Layout.DOUBLE_MARGIN + Layout.SINGLE_MARGIN,
    		colWidth * cols, 
    		rowHeight * rows);
        
        int w = paletteArea.width + Layout.DOUBLE_MARGIN * 2;
        if(w < MIN_W_WIDTH) {
        	w = MIN_W_WIDTH;
        }

		setBounds(0, 0, w, paletteArea.height + Layout.DOUBLE_MARGIN*2);
	}

    @Override
    public void paint(Graphics g) { 
        super.paint(g);
        draw(g);
    }
    
    private void draw(Graphics g) {
        Color[] palette = PaletteParams.get().getColors();
        Color textColor = PaletteParams.get().isGreyed()? Color.YELLOW: Color.BLACK;
        
        int paletteIndex = colorCount - 1;
        int x = paletteArea.x;
        int y = paletteArea.y;
        
        g.setColor(Color.BLACK);
        g.fillRect(x, y, colWidth, rowHeight);
        g.drawRect(x, y, colWidth, rowHeight);
        g.setColor(Color.WHITE);
        g.drawString(String.format("%d", paletteIndex--), x + Layout.HALF_MARGIN, y + Layout.SINGLE_MARGIN);
        y += rowHeight;
        
        g.setColor(Color.WHITE);
        g.fillRect(x, y, colWidth, rowHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, colWidth, rowHeight);
        g.drawString(String.format("%d", paletteIndex), x + Layout.HALF_MARGIN, y + Layout.SINGLE_MARGIN);
        y += rowHeight;
        
        int row = 2;
    	paletteIndex -= step;
        for(int col = 0; paletteIndex >= 0 && col < cols; col++) {
            while(paletteIndex >= 0 && row++ < rows) {
                g.setColor(palette[paletteIndex]);
                g.fillRect(x, y, colWidth, rowHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, colWidth, rowHeight);
                g.setColor(textColor);
                g.drawString(String.format("%d", paletteIndex), x + Layout.HALF_MARGIN, y + Layout.SINGLE_MARGIN);
            	paletteIndex -= step;
                y += rowHeight;
            }
            x += colWidth;
            y = paletteArea.y;
            row = 0;
        }
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
    	if(PaletteParams.get().getCount() != colorCount) {
    		setArea();
    	}
        repaint();
	}
}
