package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsersHaveRolesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UsersHaveRolesId implements java.io.Serializable {

	// Fields

	private String userId;
	private String roleKey;

	// Constructors

	/** default constructor */
	public UsersHaveRolesId() {
	}

	/** full constructor */
	public UsersHaveRolesId(String userId, String roleKey) {
		this.userId = userId;
		this.roleKey = roleKey;
	}

	// Property accessors

	@Column(name = "user_id", nullable = false, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "role_key", nullable = false, length = 36)
	public String getRoleKey() {
		return this.roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersHaveRolesId))
			return false;
		UsersHaveRolesId castOther = (UsersHaveRolesId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null && castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getRoleKey() == castOther.getRoleKey()) || (this.getRoleKey() != null && castOther.getRoleKey() != null && this.getRoleKey().equals(castOther.getRoleKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getRoleKey() == null ? 0 : this.getRoleKey().hashCode());
		return result;
	}

}