package complexfractal.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import layoutkit.Layout;

/**
 *
 * @author Horacio Bono
 */
public class ControlWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public ControlWindow() {
        super("Complex Fractals");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        Container container = getContentPane();
        container.setLayout(null);

        FractalControlPanel fcp = new FractalControlPanel();
    	container.add(fcp);
      
    	PaletteControlPanel pcp = new PaletteControlPanel();
    	container.add(pcp);
    	
    	DrawingControlPanel dcp = new DrawingControlPanel();
    	container.add(dcp);

    	JButton btnFull = new JButton("Full window");
    	container.add(btnFull);

    	JButton btnPalette = new JButton("Palette window");
    	container.add(btnPalette);

    	DrawingArea drawingArea = new DrawingArea();
    	container.add(drawingArea);

        int margin = Layout.HALF_MARGIN;
        int y = margin;

        fcp.setLocation(margin, y);
    	y = Layout.bottomOf(fcp) + margin;
    	pcp.setLocation(margin, y);
    	y = Layout.bottomOf(pcp) + margin;
    	dcp.setLocation(margin, y);
    	y = Layout.bottomOf(dcp) + margin;
    	
    	btnFull.setBounds(margin, y, 150, 30);
    	btnFull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FullWindow(drawingArea.getParams());
            }
        });
    	
    	y = Layout.bottomOf(btnFull) + margin;
    	btnPalette.setBounds(margin, y, 150, 30);
    	btnPalette.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaletteWindow();
            }
        });

        setVisible(true);

        int x = Layout.rightOf(fcp) + margin;
		drawingArea.setBounds(x, 0, 
				getWidth()-x, getHeight()-Layout.DOUBLE_MARGIN);
        drawingArea.setSize();
        
        FractalSpec.set();
	}

}
