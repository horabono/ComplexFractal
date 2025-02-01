package complexfractal.gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import layoutkit.Layout;

/**
 *
 * @author Horacio Bono
 */
public class DrawingControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public DrawingControlPanel() {
		setBorder(new TitledBorder(null, "Drawing specs", 
			TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		int y = Layout.SINGLE_MARGIN;
        for(DrawingSpec item : DrawingSpec.values()) {
        	add(item.control);
        	item.control.setBounds(Layout.SINGLE_MARGIN, y, 100, 25);
        	y = Layout.bottomOf(item.control);
        }
		setSize(100 + Layout.DOUBLE_MARGIN, y + Layout.HALF_MARGIN);
	}

}
