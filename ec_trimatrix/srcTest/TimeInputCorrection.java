import org.apache.commons.lang.math.NumberUtils;

import trimatrix.utils.Helper;


public class TimeInputCorrection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Helper.correctTimeInput("5"));
		System.out.println(Helper.correctTimeInput("15"));
		System.out.println(Helper.correctTimeInput("130"));
		System.out.println(Helper.correctTimeInput("2390"));
		System.out.println(Helper.correctTimeInput("12320"));
		System.out.println(Helper.correctTimeInput("102320"));


	}
}
