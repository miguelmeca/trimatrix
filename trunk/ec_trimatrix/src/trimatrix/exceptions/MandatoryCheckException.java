package trimatrix.exceptions;

public class MandatoryCheckException extends Exception {
	private String field;

	public MandatoryCheckException(String field) {
		super();
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
