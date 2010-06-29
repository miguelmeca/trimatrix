package trimatrix.structures;

public class SSearchMetaData {
	public static enum Type {
		STRING, NUMBER, VALUE
	}

	public String header;
	public String techname;
	public Type type;
	public String vvB;
	private SRange<?> range;
	public SRange getRange() {return new SRange(range);} // clone

	public SSearchMetaData(String header, String techname, Type type, SRange<?> range) {
		super();
		this.header = header;
		this.techname = techname;
		this.type = type;
		this.range = range;
	}

	public SSearchMetaData(String header, String techname, SRange<?> range, String vvB) {
		super();
		this.header = header;
		this.techname = techname;
		this.type = Type.VALUE;
		this.range = range;
		this.vvB = vvB;	}
}
