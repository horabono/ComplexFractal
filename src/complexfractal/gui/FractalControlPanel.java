package complexfractal.gui;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import complexfractal.FractalParams;
import layoutkit.Layout;

/**
 *
 * @author Horacio Bono
 */
public class FractalControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public FractalControlPanel() {
		setBorder(new TitledBorder(null, "Fractal specs", 
			TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

        ButtonGroup fractalType = new ButtonGroup();
        int y = Layout.SINGLE_MARGIN;
        int x = Layout.SINGLE_MARGIN;
        for(FractalSet item : FractalSet.values()) {
        	item.control.setBounds(x, y, 100, 25);
        	fractalType.add(item.control);
        	add(item.control);
        	x = Layout.rightOf(item.control);
        }

    	y += Layout.DOUBLE_MARGIN;
        x = Layout.SINGLE_MARGIN;
        for(FractalSpec item : FractalSpec.values()) {
        	item.label.setBounds(x, y, 100, 25);
        	item.label.setVisible(true);
        	item.control.setBounds(Layout.rightOf(item.label), y, 100, 25);
        	add(item.label);
        	add(item.control);
        	y = Layout.bottomOf(item.control);
        }

        StepDriver stepDriver = new StepDriver();
        stepDriver.addStepListener(new StepListener() {
			@Override
			public void stepMove(Step step) {
				int value = Integer.parseInt(FractalSpec.ZOOM.control.getText());
				switch(step) {
				case START:
					value /= 10;
					break;
				case PAGE_BACK:
					value /= 5;
					break;
				case STEP_BACK:
					value /= 2;
					break;
				case RESET:
					value = 100;
					break;
				case STEP_FORWARD:
					value *= 2;
					break;
				case PAGE_FORWARD:
					value *= 5;
					break;
				case END:
					value *= 10;
					break;
				}
				if(value < 1) {
					value = 1;
				}
				FractalSpec.ZOOM.control.setText(String.valueOf(value));
    			FractalParams.get().setZoom(value);
			}
        	
        });
        add(stepDriver);
        x = Layout.rightOf(FractalSpec.ZOOM.control) - stepDriver.getWidth();
        stepDriver.setLocation(x, y);
        y = Layout.bottomOf(stepDriver) + Layout.SINGLE_MARGIN;
		setSize(200 + Layout.DOUBLE_MARGIN, y);
		
		FractalSet.MANDELBROT.control.getModel().setSelected(true);;
	}
}
