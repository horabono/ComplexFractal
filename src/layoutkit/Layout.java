package layoutkit;

import java.awt.Component;

/**
 *
 * @author Horacio Bono
 */
public final class Layout {
    public static final int SINGLE_MARGIN = 16;
    public static final int DOUBLE_MARGIN = SINGLE_MARGIN * 2;
    public static final int HALF_MARGIN = SINGLE_MARGIN / 2;

    public static int rightOf(Component c) {
		return c.getX() + c.getWidth();
	}

	public static int bottomOf(Component c) {
		return c.getY() + c.getHeight();
	}
}
