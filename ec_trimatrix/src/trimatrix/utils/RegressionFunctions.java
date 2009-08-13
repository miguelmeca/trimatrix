package trimatrix.utils;

import org.apache.commons.lang.NotImplementedException;
import org.jfree.data.function.Function2D;

import trimatrix.exceptions.ArrayNotEvenException;
import trimatrix.exceptions.OutOfBoundsException;

/**
 * @author reich
 * 
 */
public class RegressionFunctions {
	public static final int EXP_REGRESSION = 0;
	public static final int LIN_REGRESSION = 1;
	public static final int LOG_REGRESSION = 2;
	private static final int SP = 4;

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

	/**
	 * Calculate function value for x value
	 * 
	 * @param x
	 * @return y
	 */
	public double getY(double x) {
		if (result == null)
			return 0;
		return roundSignificant(result.approxFunction.execute(result.a,
				result.b, x), SP);
	}
	
	/**
	 * Inverse calculate function for y value
	 * @param y
	 * @return
	 */
	public double getX(double y) {
		if (result == null)
			return 0;
		return roundSignificant(result.approxFunction.executeInv(result.a,
				result.b, y), SP);
	}

	/**
	 * Function for JFreeChart implementation
	 * 
	 * @return Function
	 */
	public Function2D getRegressionFunction2D() {
		return new Function2D() {
			public double getValue(double arg0) {
				return getY(arg0);
			};
		};
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
			xyArrConv[i + 1] = Math.log(xyArr[i + 1]);
		}

		RegressionResult abr = calculateLinearRegression(xyArrConv);
		if (abr == null)
			return null;
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

		RegressionResult abr = calculateOneMinusExponentialRegression(xyArr,
				lim);

		if (abr == null)
			return null;
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

	/**
	 * Function for rounding
	 * @param d	value
	 * @param significantPrecision	precision
	 * @return	rounded value	
	 */
	private static double roundSignificant(double d, int significantPrecision) {
		if (d == 0 || significantPrecision < 1 || significantPrecision > 14)
			return d;
		double mul10 = 1;
		double minVal = Math.pow(10, significantPrecision - 1);
		while (Math.abs(d) < minVal) {
			mul10 *= 10;
			d *= 10;
		}
		return Math.round(d) / mul10;
	}
	
	/**
	 * Get correlation factor by Pearson
	 * @param xyArr1	first xy value array 
	 * @param xyArr2	second xy value array
	 * @return correlation
	 */
	public static Double getPearsonCorrelation(double[] xyArr1,double[] xyArr2){
		if (xyArr1 == null || xyArr1.length < 2 || xyArr1.length % 2 != 0)
			return null;
		if (xyArr2 == null || xyArr2.length < 2 || xyArr2.length % 2 != 0)
			return null;
		
        double sum_sq_x = 0;

        double sum_sq_y = 0;
        double sum_coproduct = 0;

        double mean_x = xyArr1[0];
        double mean_y = xyArr2[0];

        for(int i=2;i<xyArr1.length+1;i+=1){
            double sweep =Double.valueOf(i-1)/i;

            double delta_x = xyArr1[i-1]-mean_x;
            double delta_y = xyArr2[i-1]-mean_y;

            sum_sq_x += delta_x * delta_x * sweep;
            sum_sq_y += delta_y * delta_y * sweep;

            sum_coproduct += delta_x * delta_y * sweep;

            mean_x += delta_x / i;
            mean_y += delta_y / i;
        }
        
        double pop_sd_x = Math.sqrt(sum_sq_x/xyArr1.length);
        double pop_sd_y = Math.sqrt(sum_sq_y/xyArr1.length);

        double cov_x_y = sum_coproduct / xyArr1.length;
        return cov_x_y / (pop_sd_x*pop_sd_y);
    }
	
	public static double getYFromMultiLinearFunction(double[] xyArr, double x) throws OutOfBoundsException, ArrayNotEvenException {
		double y = 0;
		int length = xyArr.length;
		if (xyArr == null || length < 2 || length % 2 != 0)
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

	public static class RegressionResult {
		double a;
		double b;
		double rr;
		String titel;
		String formel;
		IApproxFunction approxFunction;
		
		public String getFormel() { return formel; }
	}

	interface IApproxFunction {
		double execute(double a, double b, double x);
		double executeInv(double a, double b, double x);
	}

	interface IFunctionFromX {
		double execute(double x, Object helpObject);
	}
}
