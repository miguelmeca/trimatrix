package trimatrix.db;

/**
 * AbstractTAuthorizations entity provides the base persistence definition of
 * the TAuthorizations entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTAuthorizations implements java.io.Serializable {

	// Fields

	private TAuthorizationsId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTAuthorizations() {
	}

	/** minimal constructor */
	public AbstractTAuthorizations(TAuthorizationsId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTAuthorizations(TAuthorizationsId id, String description,
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