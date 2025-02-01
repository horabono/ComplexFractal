package complexfractal.gui;

import javax.swing.JFrame;

import layoutkit.Layout;

/**
 *
 * @author Horacio Bono
 */
public class FullWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public FullWindow(FieldParams params) {
        super("Complex Fractals");
        
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        DrawingArea drawingArea = new DrawingArea();
        getContentPane().add(drawingArea);

        setVisible(true);   

        drawingArea.setBounds(0, 0, getWidth(), 
				getHeight()-Layout.DOUBLE_MARGIN);
        drawingArea.setSize(params);
	}
}
