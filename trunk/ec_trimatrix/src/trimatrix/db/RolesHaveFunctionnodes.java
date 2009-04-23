package trimatrix.db;

/**
 * RolesHaveFunctionnodes entity. @author MyEclipse Persistence Tools
 */
public class RolesHaveFunctionnodes extends AbstractRolesHaveFunctionnodes
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public RolesHaveFunctionnodes() {
	}

	/** minimal constructor */
	public RolesHaveFunctionnodes(RolesHaveFunctionnodesId id) {
		super(id);
	}

	/** full constructor */
	public RolesHaveFunctionnodes(RolesHaveFunctionnodesId id, Integer node,
			Integer parent, Integer order) {
		super(id, node, parent, order);
	}

}
