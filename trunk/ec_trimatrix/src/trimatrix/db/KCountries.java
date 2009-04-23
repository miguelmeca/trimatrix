package trimatrix.db;

/**
 * KCountries entity. @author MyEclipse Persistence Tools
 */
public class KCountries extends AbstractKCountries implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public KCountries() {
	}

	/** minimal constructor */
	public KCountries(String key) {
		super(key);
	}

	/** full constructor */
	public KCountries(String key, String currencyKey) {
		super(key, currencyKey);
	}

}
