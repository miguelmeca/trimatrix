package trimatrix.db;

/**
 * UsersHaveAuthorizationsId entity. @author MyEclipse Persistence Tools
 */
public class UsersHaveAuthorizationsId implements java.io.Serializable {

	// Fields

	private String userId;
	private String authorizationKey;

	// Constructors

	/** default constructor */
	public UsersHaveAuthorizationsId() {
	}

	/** full constructor */
	public UsersHaveAuthorizationsId(String userId,
			String authorizationKey) {
		this.userId = userId;
		this.authorizationKey = authorizationKey;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorizationKey() {
		return this.authorizationKey;
	}

	public void setAuthorizationKey(String authorizationKey) {
		this.authorizationKey = authorizationKey;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersHaveAuthorizationsId))
			return false;
		UsersHaveAuthorizationsId castOther = (UsersHaveAuthorizationsId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getAuthorizationKey() == castOther
						.getAuthorizationKey()) || (this.getAuthorizationKey() != null
						&& castOther.getAuthorizationKey() != null && this
						.getAuthorizationKey().equals(
								castOther.getAuthorizationKey())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getAuthorizationKey() == null ? 0 : this
						.getAuthorizationKey().hashCode());
		return result;
	}

}
