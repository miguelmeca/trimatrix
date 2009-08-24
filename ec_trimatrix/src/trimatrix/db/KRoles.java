package trimatrix.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * KRoles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "k_roles", catalog = "trimatrix")
public class KRoles implements java.io.Serializable {

	// Fields

	private String key;
	
	// Collections
	
	private Set<RolesHaveFunctionnodes> functionnodes = new HashSet<RolesHaveFunctionnodes>();

	// Constructors

	/** default constructor */
	public KRoles() {
	}

	/** full constructor */
	public KRoles(String key) {
		this.key = key;
	}

	// Property accessors
	@Id
	@Column(name = "key", unique = true, nullable = false, length = 36)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="role_key")		
	public Set<RolesHaveFunctionnodes> getFunctionnodes() {
		return functionnodes;
	}

	public void setFunctionnodes(Set<RolesHaveFunctionnodes> functionnodes) {
		this.functionnodes = functionnodes;
	}

}