package trimatrix.utils.maths;

import trimatrix.exceptions.ArrayNotEvenException;
import trimatrix.exceptions.OutOfBoundsException;

public class Helper {
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
}
