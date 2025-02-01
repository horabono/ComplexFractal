package complexfractal.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

/**
 *
 * @author Horacio Bono
 */
public enum PaletteSpec {
	UP(new JCheckBox("Palette Up")),
	GREYED(new JCheckBox("Greyed")),
	ALTERNATE(new JCheckBox("Alternate"));
	
	public final JCheckBox control;
	
	private PaletteSpec(JCheckBox control) {
		this.control = control;
		this.control.addItemListener(new ItemListener() {
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	        	JCheckBox check = (JCheckBox) e.getSource();
	        	
	        	for(PaletteSpec spec : PaletteSpec.values()) {
	        		if(spec.control == check) {
	                	PaletteParams.get().setSpec(spec);
	                	break;
	        		}
	        	}
	        }
	    });
	}
	
	public boolean isSelected() {
		return control.getModel().isSelected();
	}
}
