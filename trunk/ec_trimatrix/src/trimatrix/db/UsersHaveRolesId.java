package trimatrix.db;

/**
 * UsersHaveRolesId entity. @author MyEclipse Persistence Tools
 */
public class UsersHaveRolesId extends AbstractUsersHaveRolesId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UsersHaveRolesId() {
	}

	/** full constructor */
	public UsersHaveRolesId(String userId, String roleKey) {
		super(userId, roleKey);
	}

}
