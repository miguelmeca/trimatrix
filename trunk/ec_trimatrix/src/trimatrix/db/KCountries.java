package trimatrix.db;

/**
 * KCountries entity. @author MyEclipse Persistence Tools
 */

public class KCountries implements java.io.Serializable {

	// Fields

	private String key;
	private String currencyKey;

	// Constructors

	/** default constructor */
	public KCountries() {
	}

	/** minimal constructor */
	public KCountries(String key) {
		this.key = key;
	}

	/** full constructor */
	public KCountries(String key, String currencyKey) {
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