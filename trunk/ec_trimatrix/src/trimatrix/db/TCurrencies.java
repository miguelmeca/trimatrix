package trimatrix.db;

/**
 * TCurrencies entity. @author MyEclipse Persistence Tools
 */

public class TCurrencies implements java.io.Serializable {

	// Fields

	private TCurrenciesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TCurrencies() {
	}

	/** minimal constructor */
	public TCurrencies(TCurrenciesId id) {
		this.id = id;
	}

	/** full constructor */
	public TCurrencies(TCurrenciesId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TCurrenciesId getId() {
		return this.id;
	}

	public void setId(TCurrenciesId id) {
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