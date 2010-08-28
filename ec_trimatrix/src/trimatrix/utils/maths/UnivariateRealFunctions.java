package trimatrix.utils.maths;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.interpolation.DividedDifferenceInterpolator;
import org.apache.commons.math.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionNewtonForm;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;
import org.jfree.data.function.Function2D;

public class UnivariateRealFunctions extends AFunctions {

	public static enum Type {
		SPLINE, NEVILLE, DIVIDEDDIFFERENCE
	}


	private UnivariateRealInterpolator interpolator;

	private UnivariateRealResult result;
	public UnivariateRealResult getResult() {return result;}

	public UnivariateRealFunctions(double[] xyArr, Type type) throws MathException {
		switch (type) {
		case SPLINE:
			result = new UnivariateRealResult(new SplineInterpolator(), xyArr);
			break;
		case NEVILLE:
			result = new UnivariateRealResult(new NevilleInterpolator(), xyArr);
			break;
		case DIVIDEDDIFFERENCE:
			result = new UnivariateRealResult(new DividedDifferenceInterpolator(), xyArr);
			break;
		}
		result.type = type;
	}

	public static class UnivariateRealResult implements IResult {

		double[] xyValues;
		UnivariateRealFunction function;
		UnivariateRealFunction functionInv;
		Type type;

		public UnivariateRealResult(UnivariateRealInterpolator interpolator, double[] xyArr) throws MathException{
			double[] x = new double[xyArr.length / 2];
			double[] y = new double[xyArr.length / 2];

			for (int i = 0; i < xyArr.length / 2; i++) {
				x[i] = xyArr[2 * i];
				y[i] = xyArr[2 * i + 1];
			}
			this.xyValues = xyArr;
			this.function = interpolator.interpolate(x, y);
			this.functionInv = interpolator.interpolate(y, x);
		}

		@Override
		public double getCorrelation() throws FunctionEvaluationException {
			double[] xyArr = new double[xyValues.length];
			for (int i = 0; i < xyArr.length / 2; i++) {
				xyArr[i * 2] = xyValues[i * 2];
				xyArr[i * 2 + 1] = getY(xyValues[i * 2]);
			}
			return getPearsonCorrelation(xyArr, xyValues);
		}

		@Override
		public String getFormel() {
			StringBuilder formel = new StringBuilder("y =");
			double[] coefficients = new double[0];
			double[] coefficients2 = new double[0]; //for bicubic splines
			switch (type) {
			case SPLINE:
				PolynomialSplineFunction splineFunc = (PolynomialSplineFunction)function;
				coefficients = splineFunc.getPolynomials()[0].getCoefficients();
				coefficients2 = splineFunc.getPolynomials()[1].getCoefficients();
				break;
			case NEVILLE:
				PolynomialFunctionLagrangeForm lagrangeFunc = (PolynomialFunctionLagrangeForm)function;
				coefficients = lagrangeFunc.getCoefficients();
				break;
			case DIVIDEDDIFFERENCE:
				PolynomialFunctionNewtonForm newtonFunc = (PolynomialFunctionNewtonForm)function;
				coefficients = newtonFunc.getCoefficients();
				break;
			}
			// reverse array
			double[] coefficientsReverse = new double[coefficients.length];
			for (int i = 0; i < coefficients.length; i++) { coefficientsReverse[coefficients.length-i-1] = coefficients[i]; };
			coefficients = coefficientsReverse;
			// build formula
			for (int i = 0; i < coefficients.length; i++) {
				if(coefficients[i]==0d) continue;
				if (i < coefficients.length - 2) {
					formel.append(" "
							+ roundSignificant(coefficients[i], SP) + " * x ^ "
							+ (coefficients.length - i - 1) + " +");
				} else if (i < coefficients.length - 1) {
					formel.append(" "
							+ roundSignificant(coefficients[i], SP) + " * x +");
				} else {
					formel.append(" "
							+ roundSignificant(coefficients[i], SP));
				}
			}
			if(coefficients2.length>0) {
				coefficientsReverse = new double[coefficients2.length];
				for (int i = 0; i < coefficients2.length; i++) { coefficientsReverse[coefficients2.length-i-1] = coefficients2[i]; };
				coefficients = coefficientsReverse;
				// build formula
				formel.append(" / y =");
				for (int i = 0; i < coefficients.length; i++) {
					if(coefficients[i]==0d) continue;
					if (i < coefficients.length - 2) {
						formel.append(" "
								+ roundSignificant(coefficients[i], SP) + " * x ^ "
								+ (coefficients.length - i - 1) + " +");
					} else if (i < coefficients.length - 1) {
						formel.append(" "
								+ roundSignificant(coefficients[i], SP) + " * x +");
					} else {
						formel.append(" "
								+ roundSignificant(coefficients[i], SP));
					}
				}
			}
			return formel.toString();
		}

		@Override
		public Function2D getFunction2D() {
			return new Function2D() {
				public double getValue(double arg0) {
					try {
						return getY(arg0);
					} catch (FunctionEvaluationException ex) {
						return 0d;
					}
				};
			};
		}

		@Override
		public Function2D getInvFunction2D() {
			return new Function2D() {
				public double getValue(double arg0) {
					try {
						return getX(arg0);
					} catch (FunctionEvaluationException ex) {
						return 0d;
					}
				};
			};
		}

		@Override
		public double getX(double y) throws FunctionEvaluationException {
			return functionInv.value(y);
		}

		@Override
		public double[] getXYValues() {
			return xyValues;
		}

		@Override
		public double getY(double x) throws FunctionEvaluationException {
			return function.value(x);
		}

	}
}
