package trimatrix.utils.maths;

import org.apache.commons.lang.NotImplementedException;

import jamlab.Polyfit;
import jamlab.Polyval;


public class PolynomialFunctions extends AFunctions{	
	
	private PolynomialResult result;

	public PolynomialFunctions(double[] xyArr, int degree, double offset) {
			result = calculatePolynomial(xyArr, degree, offset);			
	}
	
	public PolynomialResult getResult() {
		return result;
	}

	static PolynomialResult calculatePolynomial(double[] xyArr,final int degree, final double offset) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] x = new double[xyArr.length/2];
        double[] y = new double[xyArr.length/2];

		for (int i = 0; i < xyArr.length; i += 2) {
			if (xyArr[i + 1] <= 0)
				return null;
			x[i] = xyArr[i];
			y[i + 1] = xyArr[i + 1];
		}			
		PolynomialResult result = new PolynomialResult(degree);
        try {
        	result.polyfit = new Polyfit(x, y, degree);
        	result.titel = "Poly_" + degree;
//    		result.formel = "y = " + Universal.roundSignificant(result.a, SP) + " * ( 1 - e ^ (-"
//    				+ Universal.roundSignificant(result.b, SP) + " * x) )";
//    		result.approxFunction = new IApproxFunction() {
//    			public double execute(double a, double b, double x) {
//    				return a * (1 - Math.exp(-b * x));
//    			}
//    			public double executeInv(double a, double b, double y) {
//    				throw new NotImplementedException();
//    			}
//    		};
        } catch (Exception ex) {
            // TODO Log error
        	return null;
        }        
		return result;
	}
	
	public static class PolynomialResult {
		double[] factors;
		int degree;
		String titel;
		String formel;
		Polyfit polyfit;
		
		public PolynomialResult(int degree) {
			this.degree = degree;
			factors = new double[degree];
		}

		public String getFormel() { return formel; }
	}
}
