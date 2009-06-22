package utils;

import org.junit.Test;

import trimatrix.utils.RegressionFunctions;
import trimatrix.utils.RegressionFunctions.RegressionResult;

public class RegressionTest {
	@Test
	public void testRegressionFunction() {
		double[] xyArr = {1,1.9d,  2,3.3d, 3,5.7d, 9,155d};
		RegressionFunctions regression = new RegressionFunctions(RegressionFunctions.EXP_REGRESSION, xyArr, 0.0);
		for(int x = 0;x<21;x++) {
			double y = regression.getY(x);
			System.out.println(x + " : " + y);
		}
	}
	
	@Test
	public void testPearsonCorrelation() {
		double offset = 1.32;
		double[] xyArr = { 8, 1.64-offset, 10, 1.69-offset, 12, 2.04-offset, 14, 2.5-offset, 16, 3.29-offset, 18, 6.02-offset, 20, 11.76-offset, 22, 14.80-offset };
		RegressionFunctions regression = new RegressionFunctions(RegressionFunctions.EXP_REGRESSION, xyArr, offset);
		RegressionResult result = regression.getResult();
				
		double[] xyArr2 = xyArr.clone();
		
		for(int i=1;i<xyArr2.length;i+=2){
			xyArr2[i] = regression.getY(xyArr2[i-1])-offset;
			System.out.println("xyArr: " + xyArr[i-1] + " - " + xyArr[i] + "     xyArr2: " + xyArr2[i-1] + " - " + xyArr2[i]);
		}		
		double corr = RegressionFunctions.getPearsonCorrelation(xyArr, xyArr2);
		System.out.println(regression.getX(2.139));
		System.out.println("Korrelationsfaktor : " + corr);		
	}
}
