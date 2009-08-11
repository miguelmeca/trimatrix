package trimatrix.db;

/**
 * UsersHaveAuthorizations entity. @author MyEclipse Persistence Tools
 */
public class UsersHaveAuthorizations implements java.io.Serializable {

	// Fields

	private UsersHaveAuthorizationsId id;

	// Constructors

	/** default constructor */
	public UsersHaveAuthorizations() {
	}

	/** full constructor */
	public UsersHaveAuthorizations(UsersHaveAuthorizationsId id) {
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
