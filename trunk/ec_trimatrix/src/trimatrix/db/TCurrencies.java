package trimatrix.db;

/**
 * TCurrencies entity. @author MyEclipse Persistence Tools
 */
public class TCurrencies extends AbstractTCurrencies implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TCurrencies() {
	}

	/** minimal constructor */
	public TCurrencies(TCurrenciesId id) {
		super(id);
	}

	/** full constructor */
	public TCurrencies(TCurrenciesId id, String description,
			String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
