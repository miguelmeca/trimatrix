package trimatrix.db;

/**
 * EntitiesHaveLabels entity. @author MyEclipse Persistence Tools
 */
public class EntitiesHaveLabels implements
		java.io.Serializable {

	// Fields

	private EntitiesHaveLabelsId id;

	// Constructors

	/** default constructor */
	public EntitiesHaveLabels() {
	}

	/** full constructor */
	public EntitiesHaveLabels(EntitiesHaveLabelsId id) {
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
