package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolesHaveFunctionnodesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RolesHaveFunctionnodesId implements java.io.Serializable {

	// Fields

	private String roleKey;
	private String functionnodeKey;

	// Constructors

	/** default constructor */
	public RolesHaveFunctionnodesId() {
	}

	/** full constructor */
	public RolesHaveFunctionnodesId(String roleKey, String functionnodeKey) {
		this.roleKey = roleKey;
		this.functionnodeKey = functionnodeKey;
	}

	// Property accessors

	@Column(name = "role_key", nullable = false, length = 36)
	public String getRoleKey() {
		return this.roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	@Column(name = "functionnode_key", nullable = false, length = 36)
	public String getFunctionnodeKey() {
		return this.functionnodeKey;
	}

	public void setFunctionnodeKey(String functionnodeKey) {
		this.functionnodeKey = functionnodeKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolesHaveFunctionnodesId))
			return false;
		RolesHaveFunctionnodesId castOther = (RolesHaveFunctionnodesId) other;

		return ((this.getRoleKey() == castOther.getRoleKey()) || (this
				.getRoleKey() != null
				&& castOther.getRoleKey() != null && this.getRoleKey().equals(
				castOther.getRoleKey())))
				&& ((this.getFunctionnodeKey() == castOther
						.getFunctionnodeKey()) || (this.getFunctionnodeKey() != null
						&& castOther.getFunctionnodeKey() != null && this
						.getFunctionnodeKey().equals(
								castOther.getFunctionnodeKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleKey() == null ? 0 : this.getRoleKey().hashCode());
		result = 37
				* result
				+ (getFunctionnodeKey() == null ? 0 : this.getFunctionnodeKey()
						.hashCode());
		return result;
	}

}