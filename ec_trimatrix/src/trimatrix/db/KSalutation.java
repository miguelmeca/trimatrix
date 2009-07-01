package trimatrix.db;

/**
 * KSalutation entity. @author MyEclipse Persistence Tools
 */

public class KSalutation implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public KSalutation() {
	}

	/** full constructor */
	public KSalutation(String key) {
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