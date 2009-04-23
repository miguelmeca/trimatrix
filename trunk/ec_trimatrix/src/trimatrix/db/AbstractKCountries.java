package trimatrix.db;

/**
 * AbstractKCountries entity provides the base persistence definition of the
 * KCountries entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractKCountries implements java.io.Serializable {

	// Fields

	private String key;
	private String currencyKey;

	// Constructors

	/** default constructor */
	public AbstractKCountries() {
	}

	/** minimal constructor */
	public AbstractKCountries(String key) {
		this.key = key;
	}

	/** full constructor */
	public AbstractKCountries(String key, String currencyKey) {
		this.key = key;
		this.currencyKey = currencyKey;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCurrencyKey() {
		return this.currencyKey;
	}

	public void setCurrencyKey(String currencyKey) {
		this.currencyKey = currencyKey;
	}

}