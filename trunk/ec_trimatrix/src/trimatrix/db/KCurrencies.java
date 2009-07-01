package trimatrix.db;

/**
 * KCurrencies entity. @author MyEclipse Persistence Tools
 */

public class KCurrencies implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public KCurrencies() {
	}

	/** full constructor */
	public KCurrencies(String key) {
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