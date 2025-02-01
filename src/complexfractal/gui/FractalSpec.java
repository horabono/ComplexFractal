package complexfractal.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import complexfractal.FractalParams;
import layoutkit.Format;
import mathkit.Complex;

/**
 *
 * @author Horacio Bono
 */
public enum FractalSpec implements AreaListener {
	CONST_REAL(new JTextField(), new JLabel("Constant (real): ", JLabel.RIGHT)),
	CONST_IMAG(new JTextField(), new JLabel("Constant (imag): ", JLabel.RIGHT)),
	CENTER_REAL(new JTextField(), new JLabel("Center (real): ", JLabel.RIGHT)),
	CENTER_IMAG(new JTextField(), new JLabel("Center (imag): ", JLabel.RIGHT)),
	ITER(new JTextField(), new JLabel("Iterations: ", JLabel.RIGHT)),
	ZOOM(new JTextField(), new JLabel("Zoom: ", JLabel.RIGHT));
	
	public final JTextField control;
	public final JLabel label;
    
    public static void set() {
    	FractalParams params = FractalParams.get();
        CONST_REAL.setText("" + params.getConstant().getReal());
        CONST_IMAG.setText("" + params.getConstant().getImag());
        CENTER_REAL.setText("" + params.getCenter().getReal());
        CENTER_IMAG.setText("" + params.getCenter().getImag());
        ITER.setText("" + params.getIter());
        ZOOM.setText("" + params.getZoom());
    }
	
	private FractalSpec(JTextField control, JLabel label) {
		this.control = control;
		this.label = label;

		this.control.setHorizontalAlignment(JTextField.RIGHT);
		
		this.control.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	JTextField text = (JTextField) e.getSource();
	        	FractalSpec[] specs = FractalSpec.values();
	        	int i = 0;
	        	
	        	for(FractalSpec fs : specs) {
	        		if(fs.control == text) {
	        			specs[(i+1) % specs.length].control.requestFocus();
	                	break;
	        		}
	        		i++;
	        	}
	        }
	    });
		
		this.control.addFocusListener(new FocusListener() {
	        @SuppressWarnings("incomplete-switch")
			@Override
	        public void focusLost(FocusEvent e) {
	        	JTextField text = (JTextField) e.getSource();
	            text.setText(text.getText().replaceAll(",", "."));

	            boolean isOk = false;
	        	FractalSpec spec = null;
	        	for(FractalSpec fs : FractalSpec.values()) {
	        		if(fs.control == text) {
	        			spec = fs;
	                	break;
	        		}
	        	}
	            
	            switch(spec) {
	    		case CONST_REAL:
	    		case CONST_IMAG:
	    		case CENTER_REAL:
	    		case CENTER_IMAG:
		            try {
			            switch(spec) {
			    		case CONST_REAL:
			    		case CONST_IMAG:
			    			FractalParams.get().setConstant(new Complex(
		    					Double.parseDouble(CONST_REAL.getText()),
		    					Double.parseDouble(CONST_IMAG.getText())));
		                    break;
			    		case CENTER_REAL:
			    		case CENTER_IMAG:
			    			FractalParams.get().setCenter(new Complex(
		    					Double.parseDouble(CENTER_REAL.getText()),
		    					Double.parseDouble(CENTER_IMAG.getText())));
				        }
		                isOk = true;
		            } catch (NumberFormatException ex) {
		                isOk = false;
		            }
		            break;
	    		case ITER:
	    		case ZOOM:
		            try {
		                int value = Integer.parseInt(text.getText());
		            	switch(spec) {
			    		case ITER:
			    			PaletteParams.get().setCount((short)value);
			    			FractalParams.get().setIter((short)value);
		                    break;
			    		case ZOOM:
			    			FractalParams.get().setZoom(value);
		            	}
		                isOk = true;
		            } catch (NumberFormatException ex) {
		                isOk = false;
		            }
	    		}

	            text.setForeground(isOk? Color.BLACK: Color.RED);
	        }

	        @Override
	        public void focusGained(FocusEvent e) { }
	    });

		DrawingArea.addAreaListener(this);
	}
	
	private void setText(String text) {
		control.setText(text);
	}
	
	private String getText() {
		return control.getText();
	}

	@Override
	public void centerChanged(Complex center) {
        CENTER_REAL.setText(Format.REAL_FORMAT.format(center.getReal()));
        CENTER_IMAG.setText(Format.REAL_FORMAT.format(center.getImag()));

        FractalParams.get().setCenter(center);
	}

}
