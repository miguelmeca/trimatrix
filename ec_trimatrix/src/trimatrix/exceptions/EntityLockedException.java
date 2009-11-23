package trimatrix.exceptions;

public class EntityLockedException extends Exception {
	String user;

	public EntityLockedException(String user) {
		super();
		this.user = user;
	}
	
	public String getUser() { return user; }
}
