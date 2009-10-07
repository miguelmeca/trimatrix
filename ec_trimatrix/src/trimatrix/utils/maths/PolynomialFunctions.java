package trimatrix.utils.maths;

import jamlab.Polyfit;
import jamlab.Polyval;


public class PolynomialFunctions extends AFunctions{	
	
	private PolynomialResult result;

	public PolynomialFunctions(double[] xyArr, int degree) {
			result = calculatePolynomial(xyArr, degree);			
	}
	
	public PolynomialResult getResult() {
		return result;
	}

	static PolynomialResult calculatePolynomial(double[] xyArr,final int degree) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] x = new double[xyArr.length/2];
        double[] y = new double[xyArr.length/2];

		for (int i = 0; i < xyArr.length/2 ; i++) {
			x[i] = xyArr[2*i];
			y[i] = xyArr[2*i+1];
		}			
		PolynomialResult result = new PolynomialResult(degree);
        try {
        	result.polyfit = new Polyfit(x, y, degree);
        	result.polyfitInv = new Polyfit(y, x, degree);
        	result.titel = "Poly_" + degree;        	
        	
        	double[] coefficients = result.polyfit.getPolynomialCoefficients();
        	for(int i = 0;i<coefficients.length;i++) {
       			if(i<coefficients.length-2) {
       				result.formel.append(" " + roundSignificant(coefficients[i], SP) + " * x ^ " + (coefficients.length-i-1) + " +");
       			} else if(i<coefficients.length-1) {
           				result.formel.append(" " + roundSignificant(coefficients[i], SP) + " * x +");
       			} else {
       				result.formel.append(" " + roundSignificant(coefficients[i], SP));
       			}
           	}
        } catch (Exception ex) {
            // TODO Log error
        	return null;
        }        
		return result;
	}
	
	public static class PolynomialResult implements IResult{
		double[] factors;
		int degree;
		String titel;
		StringBuilder formel = new StringBuilder("y =");
		Polyfit polyfit;
		Polyfit polyfitInv;
		
		public PolynomialResult(int degree) {
			this.degree = degree;
			factors = new double[degree];
		}
		
		public double getY(double x) {
			double[] arrX = {x};
			Polyval polyval = new Polyval(arrX, polyfit);
			return polyval.getYout()[0];
		}
		
		public double getX(double y) {
			double[] arrY = {y};
			Polyval polyval = new Polyval(arrY, polyfitInv);
			return polyval.getYout()[0];
		}

		public String getFormel() { return formel.toString(); }
	}
}
