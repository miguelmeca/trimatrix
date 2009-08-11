package trimatrix.db;

/**
 * UsersHaveRoles entity. @author MyEclipse Persistence Tools
 */
public class UsersHaveRoles implements java.io.Serializable {

	// Fields

	private UsersHaveRolesId id;

	// Constructors

	/** default constructor */
	public UsersHaveRoles() {
	}

	/** full constructor */
	public UsersHaveRoles(UsersHaveRolesId id) {
		this.id = id;
	}

	// Property accessors

	public UsersHaveRolesId getId() {
		return this.id;
	}

	public void setId(UsersHaveRolesId id) {
		this.id = id;
	}


}
