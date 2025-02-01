package complexfractal.gui;

import java.awt.Color;

/**
 *
 * @author Horacio Bono
 */
public enum Palette {
	MAGENTA(5, -3, 4, -1, 1),
	RED(4, 2, 6, 3, 5),
	YELLOW(6, -1, 2, -2, 4),
	GREEN(2, 3, 3, 1, 6),
	CYAN(3, -2, 1, -3, 2),
	BLUE(1, 1, 5, 2, 3);
	
	public final int value;
	public final int nextUp, nextDown;
	public final int incUp, incDown;
	public final float red, green, blue;
	
	private Palette(int value, int incUp, int nextUp, int incDown, int nextDown) {
		this.value = value;
		this.incUp = incUp;
		this.nextUp = nextUp;
		this.incDown = incDown;
		this.nextDown = nextDown;
		
		this.red = (value & 4) == 0? 0: 1;
		this.green = (value & 2) == 0? 0: 1;
		this.blue = (value & 1) == 0? 0: 1;
	}
	
	public Palette next(boolean up) {
		return get(up? nextUp: nextDown);
	}
	
	public int inc(boolean up) {
		return up? incUp: incDown;
	}
	
	public Color color() {
		return new Color(red, green, blue);
	}
	
	public static Palette get(int value) {
		switch(value) {
		case 1:
			return BLUE;
		case 2:
			return GREEN;
		case 3:
			return CYAN;
		case 4:
			return RED;
		case 5:
			return MAGENTA;
		case 6:
			return YELLOW;
		}
		return null;
	}
	
}
