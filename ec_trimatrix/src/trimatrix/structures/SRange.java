package trimatrix.structures;

public class SRange<T> {
	public static enum Operator {
		EQ, GT, LT, BT, LIKE
	}

	public String field;
	public Boolean invert;
	public Operator operator;
	public T low;
	public T high;

	public SRange(String field, Boolean invert, Operator operator, T low, T high) {
		super();
		this.field = field;
		this.invert = invert;
		this.operator = operator;
		this.low = low;
		this.high = high;
	}

	public SRange() { };
}
