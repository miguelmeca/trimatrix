package trimatrix.db;

/**
 * AbstractEntitiesHaveLabels entity provides the base persistence definition of
 * the EntitiesHaveLabels entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractEntitiesHaveLabels implements
		java.io.Serializable {

	// Fields

	private EntitiesHaveLabelsId id;

	// Constructors

	/** default constructor */
	public AbstractEntitiesHaveLabels() {
	}

	/** full constructor */
	public AbstractEntitiesHaveLabels(EntitiesHaveLabelsId id) {
		this.id = id;
	}

	// Property accessors

	public EntitiesHaveLabelsId getId() {
		return this.id;
	}

	public void setId(EntitiesHaveLabelsId id) {
		this.id = id;
	}

}