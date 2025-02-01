package complexfractal.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import complexfractal.FractalParams;
import layoutkit.Format;
import layoutkit.Layout;
import mathkit.RArea;

/**
 *
 * @author Horacio Bono
 */
public final class FractalDrawer {
    private static final double DEFAULT_MARK = 1.0;
    private static final int MAX_PX_MARK = 160;
    private static final int MIN_PX_MARK = 64;
    
    private static final BasicStroke DASHED_LINE = 
        new BasicStroke(1, BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);

    private final Graphics g;
    private final short[][] matrix;
    private final RArea area;
    private final double pxUnit; 
    private final int width, height; 
    
    private double markGap;
    private double minY, minX;
    private int pxGap;
    private int pxMinY, pxMinX;

    public FractalDrawer(Graphics g, short[][] matrix, FieldParams params) {
        this.matrix = matrix;
    	this.area = params.area;
        this.g = g;
    	g.setFont(new Font("Fixedsys", Font.BOLD, 12));
        
        width = matrix[0].length;
        height = matrix.length;
        pxUnit = width / area.getWidth(); 
        
        setAxis();
    }
    
	public void print() {
        System.out.println("area = " + area);
        System.out.println("pxUnit = " + pxUnit);
        System.out.println("width = " + width);
        System.out.println("height = " + height);
        System.out.println("markGap = " + markGap);
        System.out.println("minY = " + minY);
        System.out.println("minX = " + minX);
        System.out.println("pxGap = " + pxGap);
        System.out.println("pxMinY = " + pxMinY);
        System.out.println("pxMinX = " + pxMinX);
    }
    
    public void draw() {
        if(matrix == null) {
            return;
        }

        DrawingParams params = DrawingParams.get();
        int marked = params.getMarked();
        
        if(marked < 0) {
            fullDraw();
        } else {
            markedDraw(marked);
        }
        
        if(params.isHgrid()) {
            drawHGrid();
        }
        
        if(params.isVgrid()) {
            drawVGrid();
        }
        
        if(params.isOrigin()) {
            drawOrigin();
        }
        
        if(params.isCenter()) {
            drawCenter();
        }
        
        if(params.isFrame()) {
            drawFrame();
        }
        
        if(params.isSpec()) {
            drawSpec();
        }
    }
    
    private void setAxis() {
        int pxMark;
        int gaps;

        markGap = DEFAULT_MARK;
        pxMark = (int)(markGap * pxUnit + .5);

        while(pxMark > MAX_PX_MARK) {
            markGap /= 2;
            pxMark = (int)(markGap * pxUnit + .5);

            if(pxMark > MAX_PX_MARK) {
                markGap /= 5;
                pxMark = (int)(markGap * pxUnit + .5);
            }
        }

        while(pxMark < MIN_PX_MARK) {
            markGap *= 2;
            pxMark = (int)(markGap * pxUnit + .5);

            if(pxMark < MIN_PX_MARK) {
                markGap *= 5;
                pxMark = (int)(markGap * pxUnit + .5);
            }
        }
        
        gaps = (int)(area.minX() / markGap);
        minX = markGap * gaps;
        if(minX < area.minX()) {
            minX += markGap;
        }

        gaps = (int)(area.minY() / markGap);
        minY = markGap * gaps;
        if(minY < area.minY()) {
            minY += markGap;
        }
        
        pxGap = (int)(markGap * pxUnit + .5);
        pxMinX = (int)((minX - area.minX()) * pxUnit + .5);
        pxMinY = (int)(height - (minY - area.minY()) * pxUnit + .5);
    }
    
    private void fullDraw() {
        Color[] color = PaletteParams.get().getColors();
        short iter = 0;
        int y = 0;
        try {
            for(int i = height - 1; i >= 0; i--) {
                int j = 0;
                int x = 0;
                while(j < width) {
                	iter = matrix[i][j];
                    int w = 0;
                    do {
                        j++;
                        w++;
                    } while(j < width && iter == matrix[i][j]);
                    g.setColor(color[iter]);
                    g.fillRect(x, y, w, 1);
                    x += w;
                }
                y++;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
        	System.out.println("Iter=" + iter + " color.length=" + color.length);
        }
    }
    
    private void markedDraw(int marked) {
        Color[] color = PaletteParams.get().getColors();
        
        color[color.length - 2] = Color.LIGHT_GRAY;
        
        int y = 0;
        for(int i = height - 1; i >= 0; i--) {
            int j = 0;
            int x = 0;
            while(j < width) {
            	short it = matrix[i][j];
                int w = 0;
                do {
                    j++;
                    w++;
                } while(j < width && it == matrix[i][j]);
                if(it == marked) {
                    g.setColor(color[it]);
                    g.fillRect(x, y, w, 1);
                }
                x += w;
            }
            y++;
        }

        color[color.length - 2] = Color.WHITE;
    }
    
    private void drawFrame() {
        double mark;
        int x, y, z;
        int hm = Layout.HALF_MARGIN;

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);

        mark = minX;
        x = pxMinX;
        y = height;
        z = y + hm;
        
        while(x <= width) {
            g.drawLine(x, y, x, z);
            g.drawString(Format.REAL_FORMAT.format(mark), x+4, z+8);
            mark += markGap;
            x += pxGap;
        }

        mark = minY;
        y = (int)(height - (minY - area.minY()) * pxUnit);
        x = width;
        z = x + hm;
        
        while(y >= 0) {
            g.drawLine(x, y, z, y);
            g.drawString(Format.REAL_FORMAT.format(mark) + "i", z+hm, y+hm);
            mark += markGap;
            y -= pxGap;
        }
    }
    
    private void drawHGrid() {
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setStroke(DASHED_LINE);
        g2.setColor(Color.LIGHT_GRAY);

        int y = pxMinY;
        int x = 0;
        int z = x + width;

        while(y >= 0) {
            g2.drawLine(x, y, z, y);
            y -= pxGap;
        }

        g2.dispose();
    }
    
    private void drawVGrid() {
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setStroke(DASHED_LINE);
        g2.setColor(Color.LIGHT_GRAY);

        int x = pxMinX;
        int y = 0;
        int z = y + height;

        while(x <= width) {
            g2.drawLine(x, y, x, z);
            x += pxGap;
        }

        g2.dispose();
    }
    
    private void drawCenter() {
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setStroke(DASHED_LINE);
        g2.setColor(Color.RED);

        int x = 0;
        int z = x + width;
        int y = height / 2;
        g2.drawLine(x, y, z, y);

        y = 0;
        z = y + height;
        x = width / 2;
        g2.drawLine(x, y, x, z);

        g2.dispose();
    }
    
    private void drawOrigin() {
        boolean hLine = area.minY() <= 0.0 && area.maxY() >= 0.0;
        boolean vLine = area.minX() <= 0.0 && area.maxX() >= 0.0;
        
        if(hLine || vLine) {
            Graphics2D g2 = (Graphics2D)g.create();

            int x, y, z;

            g2.setStroke(DASHED_LINE);
            g2.setColor(Color.WHITE);

            if(hLine) {
                y = (int)(area.maxY() * pxUnit + .5);
                x = 0;
                z = x + width;
                g2.drawLine(x, y, z, y);
            }

            if(vLine) {
                x = (int)(-area.minX() * pxUnit + .5);
                y = 0;
                z = y + height;
                g2.drawLine(x, y, x, z);
            }

            g2.dispose();
        }
    }
    
    private void drawSpec() {
    	StringBuffer sb = new StringBuffer();
    	sb.append(FractalParams.get().toString());
    	sb.append(" | " + PaletteParams.get().toString());

    	int y = height + Layout.DOUBLE_MARGIN;
    	Graphics2D g2 = (Graphics2D)g.create();
    	g2.setColor(Color.BLACK);
    	g2.drawString(sb.toString(), Layout.SINGLE_MARGIN, y);
    	g2.dispose();
    }
  
}
