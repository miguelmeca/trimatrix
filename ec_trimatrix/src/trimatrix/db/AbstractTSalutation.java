package trimatrix.db;

/**
 * AbstractTSalutation entity provides the base persistence definition of the
 * TSalutation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTSalutation implements java.io.Serializable {

	// Fields

	private TSalutationId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTSalutation() {
	}

	/** minimal constructor */
	public AbstractTSalutation(TSalutationId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTSalutation(TSalutationId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TSalutationId getId() {
		return this.id;
	}

	public void setId(TSalutationId id) {
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