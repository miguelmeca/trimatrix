package utils;

import org.junit.Test;

import trimatrix.utils.RegressionFunctions;

public class RegressionTest {
	@Test
	public void testRegressionFunction() {
		double[] xyArr = {1,1.9d,  2,3.3d, 3,5.7d, 9,155d};
		RegressionFunctions regression = new RegressionFunctions(RegressionFunctions.EXP_REGRESSION, xyArr);
		for(int x = 0;x<21;x++) {
			double y = regression.getY(x);
			System.out.println(x + " : " + y);
		}
	}
}
