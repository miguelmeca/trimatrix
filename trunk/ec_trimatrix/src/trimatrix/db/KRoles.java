package trimatrix.db;

import java.util.HashSet;
import java.util.Set;

/**
 * KRoles entity. @author MyEclipse Persistence Tools
 */
public class KRoles extends AbstractKRoles implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public KRoles() {
	}

	/** full constructor */
	public KRoles(String key) {
		super(key);
	}
	
	private Set<RolesHaveFunctionnodes> functionnodes = new HashSet<RolesHaveFunctionnodes>();

	public Set<RolesHaveFunctionnodes> getFunctionnodes() {
		return functionnodes;
	}

	public void setFunctionnodes(Set<RolesHaveFunctionnodes> functionnodes) {
		this.functionnodes = functionnodes;
	}

}
