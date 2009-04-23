package trimatrix.exceptions;

public class EmailNotValidException extends Exception {
	private String email;

	public EmailNotValidException(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
