package trimatrix.exceptions;

import trimatrix.utils.Helper;

public class OutOfBoundsException extends Exception {
	double low;
	double high;
	double value;

	@Override
	public String getMessage() {
		Object[] args = {value, low, high};
		return  String.format(Helper.getMessages("out_of_bound"), args);
	}

	public OutOfBoundsException(double low, double high, double value) {
		super();
		this.low = low;
		this.high = high;
		this.value = value;
	}

	@Override
	public String toString() {
		return getMessage();
	};
}
