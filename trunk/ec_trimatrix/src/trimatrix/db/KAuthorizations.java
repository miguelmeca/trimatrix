package trimatrix.db;

/**
 * KAuthorizations entity. @author MyEclipse Persistence Tools
 */
public class KAuthorizations extends AbstractKAuthorizations implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public KAuthorizations() {
	}

	/** minimal constructor */
	public KAuthorizations(String key) {
		super(key);
	}

	/** full constructor */
	public KAuthorizations(String key, String entityKey, String entityField,
			String valueLow, Boolean exclusive, String valueHigh) {
		super(key, entityKey, entityField, valueLow, exclusive, valueHigh);
	}

}
