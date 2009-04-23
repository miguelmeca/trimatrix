package trimatrix.db;

/**
 * AbstractTRoles entity provides the base persistence definition of the TRoles
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTRoles implements java.io.Serializable {

	// Fields

	private TRolesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTRoles() {
	}

	/** minimal constructor */
	public AbstractTRoles(TRolesId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTRoles(TRolesId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TRolesId getId() {
		return this.id;
	}

	public void setId(TRolesId id) {
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