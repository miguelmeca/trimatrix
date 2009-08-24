package trimatrix.db;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RolesHaveFunctionnodes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles_have_functionnodes", catalog = "trimatrix")
public class RolesHaveFunctionnodes implements java.io.Serializable {

	// Fields

	private RolesHaveFunctionnodesId id;
	private Integer node;
	private Integer parent;
	private Integer order;

	// Constructors

	/** default constructor */
	public RolesHaveFunctionnodes() {
	}

	/** minimal constructor */
	public RolesHaveFunctionnodes(RolesHaveFunctionnodesId id) {
		this.id = id;
	}

	/** full constructor */
	public RolesHaveFunctionnodes(RolesHaveFunctionnodesId id, Integer node,
			Integer parent, Integer order) {
		this.id = id;
		this.node = node;
		this.parent = parent;
		this.order = order;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "roleKey", column = @Column(name = "role_key", nullable = false, length = 36)),
			@AttributeOverride(name = "functionnodeKey", column = @Column(name = "functionnode_key", nullable = false, length = 36)) })
	public RolesHaveFunctionnodesId getId() {
		return this.id;
	}

	public void setId(RolesHaveFunctionnodesId id) {
		this.id = id;
	}

	@Column(name = "node")
	public Integer getNode() {
		return this.node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	@Column(name = "parent")
	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@Column(name = "order")
	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}