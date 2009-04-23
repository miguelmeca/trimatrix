package trimatrix.db;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import trimatrix.entities.IEntityObject;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class Users extends AbstractUsers implements java.io.Serializable, IEntityObject{

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String id) {
		super(id);
	}

	/** full constructor */
	public Users(String id, String userName, String userHash,
			String languageKey, String currencyKey, Boolean locked,
			Boolean initial, Boolean active, String email, Persons person,
			Timestamp createdAt, String createdBy, Timestamp modifiedAt,
			String modifiedBy, Boolean deleted, Boolean test) {
		super(id, userName, userHash, languageKey, currencyKey, locked,
				initial, active, email, person, createdAt, createdBy, modifiedAt,
				modifiedBy, deleted, test);
	}
	
	private Set<KRoles> roles = new HashSet<KRoles>();

	public Set<KRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<KRoles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return getUserName();
	}	
}
