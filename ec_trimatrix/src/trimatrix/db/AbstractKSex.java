package trimatrix.db;

/**
 * AbstractKSex entity provides the base persistence definition of the KSex
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractKSex implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public AbstractKSex() {
	}

	/** full constructor */
	public AbstractKSex(String key) {
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