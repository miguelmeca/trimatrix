package trimatrix.db;

/**
 * AbstractRolesHaveFunctionnodesId entity provides the base persistence
 * definition of the RolesHaveFunctionnodesId entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractRolesHaveFunctionnodesId implements
		java.io.Serializable {

	// Fields

	private String roleKey;
	private String functionnodeKey;

	// Constructors

	/** default constructor */
	public AbstractRolesHaveFunctionnodesId() {
	}

	/** full constructor */
	public AbstractRolesHaveFunctionnodesId(String roleKey,
			String functionnodeKey) {
		this.roleKey = roleKey;
		this.functionnodeKey = functionnodeKey;
	}

	// Property accessors

	public String getRoleKey() {
		return this.roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getFunctionnodeKey() {
		return this.functionnodeKey;
	}

	public void setFunctionnodeKey(String functionnodeKey) {
		this.functionnodeKey = functionnodeKey;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractRolesHaveFunctionnodesId))
			return false;
		AbstractRolesHaveFunctionnodesId castOther = (AbstractRolesHaveFunctionnodesId) other;

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

	@Override
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