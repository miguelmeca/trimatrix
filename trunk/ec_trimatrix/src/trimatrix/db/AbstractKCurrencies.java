package trimatrix.db;

/**
 * AbstractKCurrencies entity provides the base persistence definition of the
 * KCurrencies entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractKCurrencies implements java.io.Serializable {

	// Fields

	private String key;

	// Constructors

	/** default constructor */
	public AbstractKCurrencies() {
	}

	/** full constructor */
	public AbstractKCurrencies(String key) {
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