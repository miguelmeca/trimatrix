package trimatrix.utils.maths;

import org.apache.commons.lang.NotImplementedException;
import org.jfree.data.function.Function2D;

import trimatrix.exceptions.ArrayNotEvenException;
import trimatrix.exceptions.OutOfBoundsException;

/**
 * @author reich
 * 
 */
public class RegressionFunctions extends AFunctions {
	public static final int EXP_REGRESSION = 0;
	public static final int LIN_REGRESSION = 1;
	public static final int LOG_REGRESSION = 2;

	private RegressionResult result;

	public RegressionFunctions(int regression, double[] xyArr, double offset) {
		switch (regression) {
			case EXP_REGRESSION:
				result = calculateExponentialRegression(xyArr, offset);
				break;
			case LIN_REGRESSION:
				result = calculateLinearRegression(xyArr);
				break;
			case LOG_REGRESSION:
				result = calculateLogarithmicRegression(xyArr);
				break;
		}
	}
	
	public RegressionResult getResult() {
		return result;
	}

	// Lineare Regression
	// y = a + b * x
	static RegressionResult calculateLinearRegression(double[] xyArr) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		int n = xyArr.length / 2;
		double xs = 0;
		double ys = 0;
		double xqs = 0;
		double yqs = 0;
		double xys = 0;

		for (int i = 0; i < xyArr.length; i += 2) {
			xs += xyArr[i];
			ys += xyArr[i + 1];
			xqs += xyArr[i] * xyArr[i];
			yqs += xyArr[i + 1] * xyArr[i + 1];
			xys += xyArr[i] * xyArr[i + 1];
		}

		RegressionResult abr = new RegressionResult();
		abr.xyValues = xyArr;
		double xm = xs / n;
		double ym = ys / n;
		double xv = xqs / n - (xm * xm);
		double yv = yqs / n - (ym * ym);
		double kv = xys / n - (xm * ym);
		abr.rr = Math.min((kv * kv) / (xv * yv), 1);
		abr.b = kv / xv;
		abr.a = ym - abr.b * xm;
		abr.titel = "Lin";
		abr.formel = "y = " + roundSignificant(abr.a, SP) + " + "
				+ roundSignificant(abr.b, SP) + " * x";
		abr.approxFunction = new IApproxFunction() {
			public double execute(double a, double b, double x) {
				return a + b * x;
			}
			public double executeInv(double a, double b, double y) {
				return (y-a)/b;
			}
		};

		return abr;
	}

	// Potenzielle Regression
	// y = a * x^b
	// Regression ueber: ln(y) = ln(a) + b * ln(x)
	static RegressionResult calculatePowerRegression(double[] xyArr) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] xyArrConv = new double[xyArr.length];

		for (int i = 0; i < xyArr.length; i += 2) {
			if (xyArr[i] <= 0 || xyArr[i + 1] <= 0)
				return null;
			xyArrConv[i] = Math.log(xyArr[i]);
			xyArrConv[i + 1] = Math.log(xyArr[i + 1]);
		}

		RegressionResult abr = calculateLinearRegression(xyArrConv);		
		if (abr == null)
			return null;
		abr.xyValues = xyArr;
		abr.a = Math.exp(abr.a);
		abr.titel = "Pow";
		abr.formel = "y = " + roundSignificant(abr.a, SP) + " * x ^ "
				+ roundSignificant(abr.b, SP);
		abr.approxFunction = new IApproxFunction() {
			public double execute(double a, double b, double x) {
				return a * Math.pow(x, b);
			}
			public double executeInv(double a, double b, double y) {
				throw new NotImplementedException();
			}
		};

		return abr;
	}

	// Logarithmische Regression
	// y = a + b * ln(x)
	static RegressionResult calculateLogarithmicRegression(double[] xyArr) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] xyArrConv = new double[xyArr.length];

		for (int i = 0; i < xyArr.length; i += 2) {
			if (xyArr[i] <= 0)
				return null;
			xyArrConv[i] = Math.log(xyArr[i]);
			xyArrConv[i + 1] = xyArr[i + 1];
		}

		RegressionResult abr = calculateLinearRegression(xyArrConv);		
		if (abr == null)
			return null;
		abr.xyValues = xyArr;
		abr.titel = "Log";
		abr.formel = "y = " + roundSignificant(abr.a, SP) + " + "
				+ roundSignificant(abr.b, SP) + " * ln(x)";
		abr.approxFunction = new IApproxFunction() {
			public double execute(double a, double b, double x) {
				return a + b * Math.log(x);
			}
			public double executeInv(double a, double b, double y) {
				throw new NotImplementedException();
			}
		};

		return abr;
	}

	// Exponentielle Regression mit Offset
	// y = a * e^(b * x)
	// Regression ueber: ln(y) = ln(a) + b * x
	static RegressionResult calculateExponentialRegression(double[] xyArr, final double offset) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] xyArrConv = new double[xyArr.length];

		for (int i = 0; i < xyArr.length; i += 2) {
			if (xyArr[i + 1] <= 0)
				return null;
			xyArrConv[i] = xyArr[i];
			xyArrConv[i + 1] = Math.log(xyArr[i + 1]-offset);
		}

		RegressionResult abr = calculateLinearRegression(xyArrConv);		
		if (abr == null)
			return null;
		abr.xyValues = xyArr;
		abr.a = Math.exp(abr.a);
		abr.titel = "Exp";
		abr.formel = "y = " + offset + " + " + roundSignificant(abr.a, SP) + " * e ^ ("
				+ roundSignificant(abr.b, SP) + " * x)";
		abr.approxFunction = new IApproxFunction() {
			public double execute(double a, double b, double x) {
				return offset + a * Math.exp(b * x);
			}
			public double executeInv(double a, double b, double y) {
				return (Math.log(y-offset) - Math.log(a)) / b;
			}
		};

		return abr;
	}
	
	// Exponentielle Regression
	// y = a * e^(b * x)
	// Regression ueber: ln(y) = ln(a) + b * x
	static RegressionResult calculateExponentialRegression(double[] xyArr) {
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		double[] xyArrConv = new double[xyArr.length];

		for (int i = 0; i < xyArr.length; i += 2) {
			if (xyArr[i + 1] <= 0)
				return null;
			xyArrConv[i] = xyArr[i];
			xyArrConv[i + 1] = Math.log(xyArr[i + 1]);
		}

		RegressionResult abr = calculateLinearRegression(xyArrConv);		
		if (abr == null)
			return null;
		abr.xyValues = xyArr;
		abr.a = Math.exp(abr.a);
		abr.titel = "Exp";
		abr.formel = "y = " + roundSignificant(abr.a, SP) + " * e ^ ("
				+ roundSignificant(abr.b, SP) + " * x)";
		abr.approxFunction = new IApproxFunction() {
			public double execute(double a, double b, double x) {
				return a * Math.exp(b * x);
			}	
			public double executeInv(double a, double b, double y) {
				return (Math.log(y) - Math.log(a)) / b;
			}
		};
		return abr;
	}

	// Gespiegelte und verschobene exponentielle Regression
	// y = a * ( 1 - e^(-b * x) )
	// Approximationsfunktion beginnt bei 0 und strebt gegen den Grenzwert
	// "limit".
	// Falls "limit" nicht bekannt ist: Iterativ naehern.
	static RegressionResult calculateOneMinusExponentialRegression(
			double[] xyArr, double limit) {
		double[] xyArrTest = new double[xyArr.length];

		for (int i = 0; i < xyArr.length; i += 2) {
			xyArrTest[i] = -xyArr[i];
			xyArrTest[i + 1] = limit - xyArr[i + 1];
		}

		RegressionResult abr = calculateExponentialRegression(xyArrTest);		
		if (abr == null)
			return null;
		abr.xyValues = xyArr;
		abr.a = limit;
		return abr;
	}

	// Gespiegelte und verschobene exponentielle Regression
	// y = a * ( 1 - e^(-b * x) )
	// Approximationsfunktion beginnt bei 0 und strebt gegen den Grenzwert
	// "limit".
	static RegressionResult calculateOneMinusExponentialRegression(
			double[] xyArr) {
		final double INCR_FACTOR = 1.001;
		double yMax = 0;
		if (xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0)
			return null;

		for (int i = 1; i < xyArr.length; i += 2)
			yMax = Math.max(yMax, xyArr[i]);

		double lim = searchMaximumFromFunctionFromX(yMax, INCR_FACTOR, xyArr,
				new IFunctionFromX() {
					public double execute(double x, Object helpObject) {
						RegressionResult abr = calculateOneMinusExponentialRegression(
								(double[]) helpObject, x);
						if (abr == null)
							return 0;
						return abr.rr;
					}
				});

		RegressionResult abr = calculateOneMinusExponentialRegression(xyArr, lim);
		if (abr == null)
			return null;
		abr.xyValues = xyArr;
		abr.titel = "1_E";
		abr.formel = "y = " + roundSignificant(abr.a, SP) + " * ( 1 - e ^ (-"
				+ roundSignificant(abr.b, SP) + " * x) )";
		abr.approxFunction = new IApproxFunction() {
			public double execute(double a, double b, double x) {
				return a * (1 - Math.exp(-b * x));
			}
			public double executeInv(double a, double b, double y) {
				throw new NotImplementedException();
			}
		};

		return abr;
	}

	// Suche den x-Wert fuer den die "FunctionFromX" ein Maximum hat
	static double searchMaximumFromFunctionFromX(double xStart,
			double incrFactor, Object helpObject, IFunctionFromX functionFromX) {
		double x1, x2, xTest;
		double y1, y2, yTest;

		x1 = x2 = xTest = xStart;
		y1 = y2 = yTest = functionFromX.execute(xTest, helpObject);

		for (int i = 0; i < 1000000; i++) {
			xTest *= incrFactor;
			yTest = functionFromX.execute(xTest, helpObject);
			if (yTest < y1) {
				x1 = xTest;
				y1 = yTest;
				break;
			}
			x2 = x1;
			x1 = xTest;
			y2 = y1;
			y1 = yTest;
		}

		for (int i = 0; i < 1000000; i++) {
			xTest = (x1 + x2) / 2;
			yTest = functionFromX.execute(xTest, helpObject);
			if (y2 >= y1) {
				x1 = xTest;
				y1 = yTest;
			} else {
				x2 = xTest;
				y2 = yTest;
			}
			if (i > 10 && Math.abs(y2 - y1) < 1.0E-12) {
				break;
			}
		}

		return xTest;
	}
	
	public static double getYFromMultiLinearFunction(double[] xyArr, double x) throws OutOfBoundsException, ArrayNotEvenException {
		double y = 0;
		int length = xyArr.length;
		if (length < 2 || length % 2 != 0)
			throw new ArrayNotEvenException();
		if (x < xyArr[0] || x > xyArr[length-2])
			throw new OutOfBoundsException(xyArr[0], xyArr[length-2], x);
		
		for (int i = 0; i<length-3; i++) {
			if (x==xyArr[i]) {
				y=xyArr[i+1];
			}
			if (x > xyArr[i] && x < xyArr[i+2]) {
				double deltaX = xyArr[i+2]-xyArr[i];
				double deltaY = xyArr[i+3]-xyArr[i+1];
				double factor = deltaY / deltaX;
				y = (x - xyArr[i])*factor + xyArr[i+1];
			} 
			if (x==xyArr[i+2]) {
				y=xyArr[i+3];
			}
		}		
		return y;
	}

	public static class RegressionResult implements IResult {
		double a;
		double b;
		double rr;
		double[] xyValues;
		String titel;
		String formel;
		IApproxFunction approxFunction;
		
		/**
		 * Calculate function value for x value
		 * 
		 * @param x
		 * @return y
		 */
		public double getY(double x) {
			return roundSignificant(approxFunction.execute(a, b, x), SP);
		}
		
		/**
		 * Inverse calculate function for y value
		 * @param y
		 * @return
		 */
		public double getX(double y) {
			return roundSignificant(approxFunction.executeInv(a,b, y), SP);
		}
		
		public double[] getXYValues() {
			return xyValues;
		}
		
		/**
		 * Returns Function2D for JFreeChart
		 * @return
		 */
		public Function2D getFunction2D() {
			return new Function2D() {
				public double getValue(double arg0) {
					return getY(arg0);
				};
			};
		}
		
		public String getFormel() { return formel; }
		
		public String getTitell() { return titel; }
		
		public double getCorrelation() {
			double[] xyArr = new double[xyValues.length];
			for(int i = 0;i<xyArr.length/2;i++) {
				xyArr[i*2] = xyValues[i*2];
				xyArr[i*2+1] = getY(xyValues[i*2]);
			}
			return getPearsonCorrelation(xyArr, xyValues);
		}
	}

	interface IFunctionFromX {
		double execute(double x, Object helpObject);
	}
	
	public interface IApproxFunction {
		double execute(double a, double b, double x);
		double executeInv(double a, double b, double x);
	}
}
