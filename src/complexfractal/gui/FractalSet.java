package complexfractal.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;

import complexfractal.FractalParams;

/**
 *
 * @author Horacio Bono
 */
public enum FractalSet {
	MANDELBROT(new JRadioButton("Mandelbrot")),
	JULIA(new JRadioButton("Julia"));
	
	public final JRadioButton control;
	
	private FractalSet(JRadioButton control) {
		this.control = control;
		this.control.addItemListener( new ItemListener() {
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	            JRadioButton rb = (JRadioButton) e.getSource();
	            FractalParams params = FractalParams.get();
	            ButtonModel model = rb.getModel();
	            if(rb == MANDELBROT.control) {
	                if(model.isSelected()) {
	                    params.setSet(MANDELBROT);
	                }
	            } else if(rb == JULIA.control) {
	                if(model.isSelected()) {
	                    params.setSet(JULIA);
	                }
	            }
	        }
		}
		);
	}
}
