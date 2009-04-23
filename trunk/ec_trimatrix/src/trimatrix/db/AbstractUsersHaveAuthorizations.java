package trimatrix.db;

/**
 * AbstractUsersHaveAuthorizations entity provides the base persistence
 * definition of the UsersHaveAuthorizations entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractUsersHaveAuthorizations implements
		java.io.Serializable {

	// Fields

	private UsersHaveAuthorizationsId id;

	// Constructors

	/** default constructor */
	public AbstractUsersHaveAuthorizations() {
	}

	/** full constructor */
	public AbstractUsersHaveAuthorizations(UsersHaveAuthorizationsId id) {
		this.id = id;
	}

	// Property accessors

	public UsersHaveAuthorizationsId getId() {
		return this.id;
	}

	public void setId(UsersHaveAuthorizationsId id) {
		this.id = id;
	}

}