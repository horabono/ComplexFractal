package complexfractal.gui;

import mathkit.Complex;

/**
 *
 * @author Horacio Bono
 */
public interface FractalListener {
    void setChanged();
    void constChanged();
    void centerChanged(Complex center);
    void iterChanged();
    void zoomChanged(int zoom);
}
