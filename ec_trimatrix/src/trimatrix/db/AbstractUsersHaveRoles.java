package trimatrix.db;

/**
 * AbstractUsersHaveRoles entity provides the base persistence definition of the
 * UsersHaveRoles entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsersHaveRoles implements java.io.Serializable {

	// Fields

	private UsersHaveRolesId id;

	// Constructors

	/** default constructor */
	public AbstractUsersHaveRoles() {
	}

	/** full constructor */
	public AbstractUsersHaveRoles(UsersHaveRolesId id) {
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