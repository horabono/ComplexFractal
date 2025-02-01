package complexfractal.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

/**
 *
 * @author Horacio Bono
 */
public enum DrawingSpec {
	FRAME(new JCheckBox("Frame")),
	HORIZONTAL(new JCheckBox("HGrid")),
	VERTICAL(new JCheckBox("VGrid")),
	CENTER(new JCheckBox("Center")),
	ORIGIN(new JCheckBox("Origin")),
	SPEC(new JCheckBox("Spec"));
	
	public final JCheckBox control;
	
	private DrawingSpec(JCheckBox control) {
		this.control = control;
		this.control.addItemListener(new ItemListener() {
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	        	JCheckBox check = (JCheckBox) e.getSource();
	        	
	        	for(DrawingSpec spec : DrawingSpec.values()) {
	        		if(spec.control == check) {
	                	DrawingParams.get().setSpec(spec);
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
