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
	public String getField() { return field; }
	public void setField(String field) { this.field = field; }

	public Operator operator;
	public String getOperator() { return operator.name(); }
	public void setOperator(String operator) { this.operator = Operator.valueOf(operator); }

	public T value;
	public T getValue() {return value;}
	public void setValue(T value) {this.value = value;}

	public SRange(String field, Operator operator, T value) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	public SRange(String field) {
		super();
		this.field = field;
	}

	public void clear() {
		field = null;
		operator = null;
		value = null;
	}

	public SRange(SRange<T> range) {
		super();
		this.field = range.field;
		this.operator = range.operator;
		this.value = range.value;
	};
}
