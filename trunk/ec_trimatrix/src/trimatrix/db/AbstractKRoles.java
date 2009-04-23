package trimatrix.db;

/**
 * AbstractKRoles entity provides the base persistence definition of the KRoles
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractKRoles implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public AbstractKRoles() {
	}

	/** full constructor */
	public AbstractKRoles(String key) {
		this.key = key;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}