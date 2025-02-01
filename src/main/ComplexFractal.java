package main;
import java.awt.EventQueue;

import complexfractal.gui.ControlWindow;

/**
 *
 * @author Horacio Bono
 */
public class ComplexFractal {

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ControlWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
