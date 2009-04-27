package trimatrix.structures;

public final class SGridMetaData {
	public static enum Component {
		FIELD, CHECKBOX, CALENDARFIELD
	}
   
    public String header;
    public String techname;
    public Component component;
    
    public SGridMetaData(){};
    
	public SGridMetaData(String header, String techname, Component component) {
		super();
		this.header = header;
		this.techname = techname;
		this.component = component;
	}      
}
