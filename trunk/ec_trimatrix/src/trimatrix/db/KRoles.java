package trimatrix.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToOne;

/**
 * KRoles entity. @author MyEclipse Persistence Tools
 */

public class KRoles implements java.io.Serializable {

	// Fields

	private String key;
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

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public Set<RolesHaveFunctionnodes> getFunctionnodes() {
		return functionnodes;
	}

	public void setFunctionnodes(Set<RolesHaveFunctionnodes> functionnodes) {
		this.functionnodes = functionnodes;
	}
}