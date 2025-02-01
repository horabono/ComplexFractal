package complexfractal.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import layoutkit.Layout;

/**
 *
 * @author Horacio Bono
 */
public class PaletteControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PaletteControlPanel() {
		setBorder(new TitledBorder(null, "Palette specs", 
			TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
        
        int x = Layout.SINGLE_MARGIN;
        int y = Layout.SINGLE_MARGIN + Layout.HALF_MARGIN;
        
		JComboBox<Palette> cmbPalette = new JComboBox<>();
        cmbPalette.setBounds(x, y, 100, 25);
    	add(cmbPalette);
        
        for(Palette palette : Palette.values()) {
        	cmbPalette.addItem(palette);
        }
        y = Layout.bottomOf(cmbPalette);

    	for(PaletteSpec spec : PaletteSpec.values()) {
        	spec.control.setBounds(x, y, 100, 25);
        	add(spec.control);
            y = Layout.bottomOf(spec.control);
        }
    	setSize(100 + Layout.DOUBLE_MARGIN, y + Layout.HALF_MARGIN);
        
        cmbPalette.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PaletteSpec.GREYED.control.setSelected(false);
                Palette palette = (Palette) cmbPalette.getSelectedItem();
                PaletteParams.get().setPalette(palette);
			}
        });
	}

}
