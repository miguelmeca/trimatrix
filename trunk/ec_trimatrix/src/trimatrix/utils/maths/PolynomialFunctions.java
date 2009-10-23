package trimatrix.utils.maths;

import org.jfree.data.function.Function2D;

import jamlab.Polyfit;
import jamlab.Polyval;

public class PolynomialFunctions extends AFunctions {

	private PolynomialResult result;

	public PolynomialFunctions(double[] xyArr, int degree) throws Exception {
		result = calculatePolynomial(xyArr, degree);
	}

	public PolynomialResult getResult() {
		return result;
	}

	static PolynomialResult calculatePolynomial(double[] xyArr, final int degree)
			throws Exception {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] x = new double[xyArr.length / 2];
		double[] y = new double[xyArr.length / 2];

		for (int i = 0; i < xyArr.length / 2; i++) {
			x[i] = xyArr[2 * i];
			y[i] = xyArr[2 * i + 1];
		}
		PolynomialResult result = new PolynomialResult(degree);

		result.polyfit = new Polyfit(x, y, degree);
		result.polyfitInv = new Polyfit(y, x, degree);
		result.titel = "Poly_" + degree;
		result.xyValues = xyArr;

		double[] coefficients = result.polyfit.getPolynomialCoefficients();
		for (int i = 0; i < coefficients.length; i++) {
			if (i < coefficients.length - 2) {
				result.formel.append(" "
						+ roundSignificant(coefficients[i], SP) + " * x ^ "
						+ (coefficients.length - i - 1) + " +");
			} else if (i < coefficients.length - 1) {
				result.formel.append(" "
						+ roundSignificant(coefficients[i], SP) + " * x +");
			} else {
				result.formel.append(" "
						+ roundSignificant(coefficients[i], SP));
			}
		}
		return result;
	}

	public static class PolynomialResult implements IResult {
		double[] factors;
		double[] xyValues;
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
			double[] arrX = { x };
			Polyval polyval = new Polyval(arrX, polyfit);
			return polyval.getYout()[0];
		}

		public double getX(double y) {
			double[] arrY = { y };
			Polyval polyval = new Polyval(arrY, polyfitInv);
			return polyval.getYout()[0];
		}

		public double[] getXYValues() {
			return xyValues;
		}

		public double getCorrelation() {
			double[] xyArr = new double[xyValues.length];
			for (int i = 0; i < xyArr.length / 2; i++) {
				xyArr[i * 2] = xyValues[i * 2];
				xyArr[i * 2 + 1] = getY(xyValues[i * 2]);
			}
			return getPearsonCorrelation(xyArr, xyValues);
		}

		/**
		 * Returns Function2D for JFreeChart
		 * 
		 * @return
		 */
		public Function2D getFunction2D() {
			return new Function2D() {
				public double getValue(double arg0) {
					return getY(arg0);
				};
			};
		}

		public String getFormel() {
			return formel.toString();
		}

		public double[] getFactors() {
			return factors;
		}

		public String getTitel() {
			return titel;
		}

		public int getDegree() {
			return degree;
		}		
	}
}
