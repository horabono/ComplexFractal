package mathkit;

/** 
* 
* @author Horacio Bono
*/
public class Complex {
    private double real;
    private double imag;
    
    private static final Complex UNITY = new Complex(1, 0);

    public Complex() {
        real = imag = 0.0;
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex(Complex other) {
        real = other.real;
        imag = other.imag;
    }

    public Complex(RPoint p) {
        real = p.getX();
        imag = p.getY();
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public double mod() {
        return Math.sqrt(real * real + imag * imag);
    }

    @Override
    public String toString() {
        String re = real + " ";
        String im = imag < 0? imag + " i": "+" + imag + " i";
        return re + im;
    }

    public double arg() {
        return Math.atan2(imag, real);
    }

    @Override
    public final boolean equals(Object z) {
        boolean eq = z instanceof Complex;

        if(eq) {
            Complex a = (Complex) z;
            eq = real == a.real && imag == a.imag;
        }
        
        return eq;
    }
    
    public Complex set(double real, double imag) {
        this.real = real;
        this.imag = imag;
        return this;
    }

    public Complex set(Complex other) {
        real = other.real;
        imag = other.imag;
        return this;
    }

    public Complex set(RPoint p) {
        real = p.getX();
        imag = p.getY();
        return this;
    }

    public Complex setReal(double real) {
        this.real = real;
        return this;
    }

    public Complex setImag(double imag) {
        this.imag = imag;
        return this;
    }

    public Complex reset() {
        real = imag = 0.0;
        return this;
    }

    public Complex add(Complex z) {
        real += z.real;
        imag += z.imag;
        return this;
    }
    
    public Complex addReal(double d) {
        real += d;
        return this;
    }
    
    public Complex addImag(double d) {
        imag += d;
        return this;
    }

    public Complex subtract(Complex z) {
        real -= z.real;
        imag -= z.imag;
        return this;
    }

    public Complex multiply(Complex z) {
        double _real = real * z.real - imag * z.imag;
        double _imag = real * z.imag + imag * z.real;
        return set(_real, _imag);
    }
    
    public Complex multiply(double d) {
        return set(real * d, imag * d);
    }

    public Complex divide(Complex z) {
        double mod = z.mod();
        return multiply(conjugate(z)).divide(mod * mod);
    }
    
    public Complex divide(double d) {
        return set(real / d, imag / d);
    }

    public Complex pow(int power) {
        double re = 1;
        double im = 0;

        for(int i = 0; i < power; i++) {
            double _real = re * real - im * imag;
            double _imag = re * imag + im * real;
            re = _real;
            im = _imag;
        }
        return set(re, im);
    }

    public Complex conjugate() {
    	imag = -imag;
        return this;
    }

    public Complex square() {
        double _real = real * real - imag * imag;
        double _imag = 2 * real * imag;
        return set(_real, _imag);
    }

    public Complex inverse() {
        return set(divide(UNITY, this));
    }
    
    public static Complex unity() {
    	return new Complex(UNITY);
    }

    public static Complex add(Complex z1, Complex z2) {
        return new Complex(z1.real + z2.real, z1.imag + z2.imag);
    }

    public static Complex subtract(Complex z1, Complex z2) {
        return new Complex(z1.real - z2.real, z1.imag - z2.imag);
    }

    public static Complex multiply(Complex z1, Complex z2) {
        double _real = z1.real * z2.real - z1.imag * z2.imag;
        double _imag = z1.real * z2.imag + z1.imag * z2.real;
        return new Complex(_real, _imag);
    }
    
    public static Complex multiply(Complex z, double d) {
        return new Complex(z.real * d, z.imag * d);
    }

    public static Complex divide(Complex z1, Complex z2) {
        Complex result = multiply(z1, conjugate(z2));
        double mod = z2.mod();
        return result.divide(mod * mod);
    }
    
    public static Complex divide(Complex z, double d) {
        return new Complex(z.real / d, z.imag / d);
    }

    public static Complex exp(Complex z) {
        double re = z.real;
        double im = z.imag;
        double p = Math.exp(re);

        re = p * Math.cos(im);
        im = p * Math.sin(im);

        return new Complex(re, im);
    }

    public static Complex pow(Complex z, int power) {
    	Complex result = unity();
        int p = Math.abs(power);

        for(int i = 0; i < p; i++) {
            double _real = result.real * z.real - result.imag * z.imag;
            double _imag = result.real * z.imag + result.imag * z.real;
            result.set(_real, _imag);
        }
        
        if(power < 0) {
        	result.inverse();
        }
        
        return result;
    }

    public static Complex sin(Complex z) {
        double x = Math.exp(z.imag);
        double x_inv = 1/x;
        double r = Math.sin(z.real) * (x + x_inv) / 2;
        double i = Math.cos(z.real) * (x - x_inv) / 2;
        return new Complex(r, i);
    }

    public static Complex cos(Complex z) {
        double x = Math.exp(z.imag);
        double x_inv = 1/x;
        double r = Math.cos(z.real) * (x + x_inv) / 2;
        double i = -Math.sin(z.real) * (x - x_inv) / 2;
        return new Complex(r, i);
    }

    public static Complex tan(Complex z) {
        return divide(sin(z), cos(z));
    }

    public static Complex cot(Complex z) {
        return divide(UNITY, tan(z));
    }

    public static Complex sec(Complex z) {
        return divide(UNITY, cos(z));
    }

    public static Complex cosec(Complex z) {
        return divide(UNITY, sin(z));
    }

    public static Complex conjugate(Complex z) {
        return new Complex(z.real, -z.imag);
    }

}