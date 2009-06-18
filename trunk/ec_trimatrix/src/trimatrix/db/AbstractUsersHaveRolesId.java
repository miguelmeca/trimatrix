package trimatrix.db;

/**
 * AbstractUsersHaveRolesId entity provides the base persistence definition of
 * the UsersHaveRolesId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsersHaveRolesId implements java.io.Serializable {

	// Fields

	private String userId;
	private String roleKey;

	// Constructors

	/** default constructor */
	public AbstractUsersHaveRolesId() {
	}

	/** full constructor */
	public AbstractUsersHaveRolesId(String userId, String roleKey) {
		this.userId = userId;
		this.roleKey = roleKey;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleKey() {
		return this.roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractUsersHaveRolesId))
			return false;
		AbstractUsersHaveRolesId castOther = (AbstractUsersHaveRolesId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getRoleKey() == castOther.getRoleKey()) || (this
						.getRoleKey() != null
						&& castOther.getRoleKey() != null && this.getRoleKey()
						.equals(castOther.getRoleKey())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoleKey() == null ? 0 : this.getRoleKey().hashCode());
		return result;
	}

}