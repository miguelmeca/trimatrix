package trimatrix.db;

/**
 * TCountries entity. @author MyEclipse Persistence Tools
 */

public class TCountries implements java.io.Serializable {

	// Fields

	private TCountriesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TCountries() {
	}

	/** minimal constructor */
	public TCountries(TCountriesId id) {
		this.id = id;
	}

	/** full constructor */
	public TCountries(TCountriesId id, String description,
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