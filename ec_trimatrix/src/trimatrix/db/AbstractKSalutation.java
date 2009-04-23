package trimatrix.db;

/**
 * AbstractKSalutation entity provides the base persistence definition of the
 * KSalutation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractKSalutation implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public AbstractKSalutation() {
	}

	/** full constructor */
	public AbstractKSalutation(String key) {
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