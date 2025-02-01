package complexfractal;

import complexfractal.gui.FieldParams;
import mathkit.Complex;

/**
 *
 * @author Horacio Bono
 */
public class FractalGenerator {
    private static final double LIMIT = 2.0;
    
    private final FieldParams params;
    
    private final short[][] matrix;

    public FractalGenerator(FieldParams params) {
        matrix = new short[params.size.getHeight()][params.size.getWidth()];
    	this.params = params;
    }

    public void createSet(FieldParams params) {
        switch(FractalParams.get().getSet()) {
            case MANDELBROT:
                createMandelbrot();
                break;
            case JULIA:
                createJulia();
                break;
        }
    }
    
    private void createMandelbrot() {
        Complex seed = FractalParams.get().getConstant();
        Complex c = new Complex();
        Complex z = new Complex();
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        ComplexScanner scanner = new ComplexScanner(params);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                c.set(scanner.getValue());
                z.set(seed);
                matrix[i][j] = getCount(z, c);
            }
        }
    }
    
    private void createJulia() {
        final Complex c = FractalParams.get().getConstant();
        final Complex z = new Complex();
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        ComplexScanner scanner = new ComplexScanner(params);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                z.set(scanner.getValue());
                matrix[i][j] = getCount(z, c);
            }
        }
    }
    
    public short[][] getMatrix() {
    	return matrix;
    }
    
    private short getCount(Complex z, Complex c) {
    	int iter = FractalParams.get().getIter();
    	short count = 0;

        while(z.mod() <= LIMIT && ++count < iter) {
            z.square().add(c);
        }
        
        return count;
    }
    
}
