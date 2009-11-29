package trimatrix.utils.maths;

import trimatrix.exceptions.ArrayNotEvenException;
import trimatrix.exceptions.OutOfBoundsException;

public class Helper {
	public static double getYFromMultiLinearFunction(double[] xyArr, double x) throws OutOfBoundsException, ArrayNotEvenException {
		double y = 0;
		int length = xyArr.length;
		if (length < 2 || length % 2 != 0)
			throw new ArrayNotEvenException();
		// special handling if request value out of bounds
		if (x < xyArr[0]) {
			double deltaX = xyArr[2]-xyArr[0];
			double deltaY = xyArr[3]-xyArr[1];
			double factor = deltaY / deltaX;
			return xyArr[1] - (xyArr[0] - x)*factor;
		}
		
		if(x > xyArr[length-2]) {
			double deltaX = xyArr[length-2]-xyArr[length-4];
			double deltaY = xyArr[length-1]-xyArr[length-3];
			double factor = deltaY / deltaX;			
			return (x - xyArr[length-2])*factor + xyArr[length-1];
		}
		// normal handling
		for (int i = 0; i<length-3; i++) {
			if (x==xyArr[i]) {
				y=xyArr[i+1];
				break; // value found leave loop
			}
			if (x > xyArr[i] && x < xyArr[i+2]) {
				double deltaX = xyArr[i+2]-xyArr[i];
				double deltaY = xyArr[i+3]-xyArr[i+1];
				double factor = deltaY / deltaX;
				y = (x - xyArr[i])*factor + xyArr[i+1];
				// value found leave loop
			} 
			if (x==xyArr[i+2]) {
				y=xyArr[i+3];
				break;	// value found leave loop
			}
		}		
		return y;
	}
}
