package complexfractal.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StepDriver extends JPanel {
	
	private ArrayList<StepListener> listeners;

	public StepDriver() {
		listeners = new ArrayList<>();
		setSize(new Dimension(28 * Step.values().length, 20));
		setLayout(new GridLayout(1, Step.values().length, 0, 0));
		for(Step step : Step.values()) {
			JButton button = new JButton(step.mark);
			button.setMargin(new Insets(0,0,0,0));
			button.setBackground(new Color(0xF0, 0xF0, 0xF0));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(StepListener listener : listeners) {
						listener.stepMove(step);
					}
				}
			});
			add(button);
		}
	}
	
	public void addStepListener(StepListener listener) {
		listeners.add(listener);
	}

}
	