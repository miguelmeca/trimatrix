package trimatrix.db;

/**
 * UsersHaveAuthorizationsId entity. @author MyEclipse Persistence Tools
 */
public class UsersHaveAuthorizationsId extends
		AbstractUsersHaveAuthorizationsId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UsersHaveAuthorizationsId() {
	}

	/** full constructor */
	public UsersHaveAuthorizationsId(String userId, String authorizationKey) {
		super(userId, authorizationKey);
	}

}
