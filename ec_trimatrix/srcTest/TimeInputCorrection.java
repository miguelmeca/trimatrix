import trimatrix.utils.HelperTime;


public class TimeInputCorrection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(HelperTime.correctTimeInput("5"));
		System.out.println(HelperTime.correctTimeInput("15"));
		System.out.println(HelperTime.correctTimeInput("130"));
		System.out.println(HelperTime.correctTimeInput("2390"));
		System.out.println(HelperTime.correctTimeInput("12320"));
		System.out.println(HelperTime.correctTimeInput("102320"));


	}
}
