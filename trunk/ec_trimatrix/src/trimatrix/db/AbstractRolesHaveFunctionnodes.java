package trimatrix.db;

/**
 * AbstractRolesHaveFunctionnodes entity provides the base persistence
 * definition of the RolesHaveFunctionnodes entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractRolesHaveFunctionnodes implements
		java.io.Serializable {

	// Fields

	private RolesHaveFunctionnodesId id;
	private Integer node;
	private Integer parent;
	private Integer order;

	// Constructors

	/** default constructor */
	public AbstractRolesHaveFunctionnodes() {
	}

	/** minimal constructor */
	public AbstractRolesHaveFunctionnodes(RolesHaveFunctionnodesId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractRolesHaveFunctionnodes(RolesHaveFunctionnodesId id,
			Integer node, Integer parent, Integer order) {
		this.id = id;
		this.node = node;
		this.parent = parent;
		this.order = order;
	}

	// Property accessors

	public RolesHaveFunctionnodesId getId() {
		return this.id;
	}

	public void setId(RolesHaveFunctionnodesId id) {
		this.id = id;
	}

	public Integer getNode() {
		return this.node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}