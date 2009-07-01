package trimatrix.db;

/**
 * TRoles entity. @author MyEclipse Persistence Tools
 */

public class TRoles implements java.io.Serializable {

	// Fields

	private TRolesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TRoles() {
	}

	/** minimal constructor */
	public TRoles(TRolesId id) {
		this.id = id;
	}

	/** full constructor */
	public TRoles(TRolesId id, String description, String descriptionLong) {
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