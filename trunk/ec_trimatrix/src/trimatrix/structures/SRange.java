package trimatrix.structures;

public class SRange<T> {
	public static enum Operator {
		EQ, NE, GT, GE, LT, LE, CT;

		public String getLiteral() {
			return "#{rr.literals.$}".replace("$", this.name().toLowerCase());
		}
	}

	public static final Operator[] STRING_OPERATORS = {Operator.EQ, Operator.NE, Operator.CT};
	public static final Operator[] NUMBER_OPERATORS = {Operator.EQ, Operator.NE, Operator.GT, Operator.GE, Operator.LT, Operator.LE};
	public static final Operator[] VALUES_OPERATORS = {Operator.EQ, Operator.NE};

	public String field;
	public Operator operator;
	public T value;

	public SRange(String field, Operator operator, T value) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	public void clear() {
		field = null;
		operator = null;
		value = null;
	}

	public SRange() { };
}
