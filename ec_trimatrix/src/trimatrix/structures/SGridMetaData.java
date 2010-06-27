package trimatrix.structures;

public final class SGridMetaData {
	private final static String DEFAULT_WIDTH = "100";

	public static enum Component {
		FIELD, CHECKBOX, CALENDARFIELD, FORMATED_DATETIME, FORMATED_DOUBLE, ICON, COLORFIELD, INDIVIDUAL
	}

    public String header;
    public String techname;
    public Component component;
    public String width;
    public String code;

    public SGridMetaData(){};

	public SGridMetaData(String header, String techname, Component component) {
		super();
		this.header = header;
		this.techname = techname;
		this.component = component;
		this.width = DEFAULT_WIDTH;
	}

	public SGridMetaData(String header, String techname, String code) {
		super();
		this.header = header;
		this.techname = techname;
		this.component = Component.INDIVIDUAL;
		this.code = code;
	}

	public SGridMetaData(String header, String techname, String width, Component component) {
		super();
		this.header = header;
		this.techname = techname;
		this.component = component;
		this.width = width;
	}
}
