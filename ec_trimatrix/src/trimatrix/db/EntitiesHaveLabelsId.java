package trimatrix.db;

/**
 * EntitiesHaveLabelsId entity. @author MyEclipse Persistence Tools
 */
public class EntitiesHaveLabelsId extends AbstractEntitiesHaveLabelsId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public EntitiesHaveLabelsId() {
	}

	/** full constructor */
	public EntitiesHaveLabelsId(String entity, String label, String personId) {
		super(entity, label, personId);
	}

}
