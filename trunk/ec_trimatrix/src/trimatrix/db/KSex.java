package trimatrix.db;

/**
 * KSex entity. @author MyEclipse Persistence Tools
 */

public class KSex implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public KSex() {
	}

	/** full constructor */
	public KSex(String key) {
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