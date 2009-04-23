package trimatrix.db;

/**
 * TRoles entity. @author MyEclipse Persistence Tools
 */
public class TRoles extends AbstractTRoles implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TRoles() {
	}

	/** minimal constructor */
	public TRoles(TRolesId id) {
		super(id);
	}

	/** full constructor */
	public TRoles(TRolesId id, String description, String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
