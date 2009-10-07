package utils.maths;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.utils.maths.PolynomialFunctions;
import trimatrix.utils.maths.PolynomialFunctions.PolynomialResult;

public class PolynomialTest {
	@Test
	public void testPolynomialFunction() {
		double[] xyArr = {1,15.8d,  2,24.6d, 3,38.4d, 4,57.2d, 10,275d};
		PolynomialFunctions polyFunction = new PolynomialFunctions(xyArr, 2);
		PolynomialResult result = polyFunction.getResult();
		Assert.assertEquals("y = 2.5 * x ^ 2 + 1.3 * x + 12.0", result.getFormel());		
	}
}
