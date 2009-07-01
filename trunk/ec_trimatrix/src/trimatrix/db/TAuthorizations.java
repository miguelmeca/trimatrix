package trimatrix.db;

/**
 * TAuthorizations entity. @author MyEclipse Persistence Tools
 */

public class TAuthorizations implements java.io.Serializable {

	// Fields

	private TAuthorizationsId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TAuthorizations() {
	}

	/** minimal constructor */
	public TAuthorizations(TAuthorizationsId id) {
		this.id = id;
	}

	/** full constructor */
	public TAuthorizations(TAuthorizationsId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TAuthorizationsId getId() {
		return this.id;
	}

	public void setId(TAuthorizationsId id) {
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