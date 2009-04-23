package trimatrix.db;

/**
 * TCountries entity. @author MyEclipse Persistence Tools
 */
public class TCountries extends AbstractTCountries implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TCountries() {
	}

	/** minimal constructor */
	public TCountries(TCountriesId id) {
		super(id);
	}

	/** full constructor */
	public TCountries(TCountriesId id, String description,
			String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
