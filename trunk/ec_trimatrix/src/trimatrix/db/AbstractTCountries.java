package trimatrix.db;

/**
 * AbstractTCountries entity provides the base persistence definition of the
 * TCountries entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTCountries implements java.io.Serializable {

	// Fields

	private TCountriesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTCountries() {
	}

	/** minimal constructor */
	public AbstractTCountries(TCountriesId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTCountries(TCountriesId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TCountriesId getId() {
		return this.id;
	}

	public void setId(TCountriesId id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionLong() {
		return this.descriptionLong;
	}

	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}

}