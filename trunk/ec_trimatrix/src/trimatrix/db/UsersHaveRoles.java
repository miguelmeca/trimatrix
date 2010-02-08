package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UsersHaveRoles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users_have_roles", catalog = "trimatrix")
public class UsersHaveRoles implements java.io.Serializable {

	// Fields

	private UsersHaveRolesId id;

	// Constructors

	/** default constructor */
	public UsersHaveRoles() {
	}

	/** full constructor */
	public UsersHaveRoles(UsersHaveRolesId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( { @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 36)),
			@AttributeOverride(name = "roleKey", column = @Column(name = "role_key", nullable = false, length = 36)) })
	public UsersHaveRolesId getId() {
		return this.id;
	}

	public void setId(UsersHaveRolesId id) {
		this.id = id;
	}

}