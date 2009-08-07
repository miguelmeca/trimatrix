package trimatrix.db;

/**
 * Entities entity. @author MyEclipse Persistence Tools
 */

public class Entities implements java.io.Serializable {

	// Fields

	private EntitiesId id;

	// Constructors

	/** default constructor */
	public Entities() {
	}

	/** full constructor */
	public Entities(EntitiesId id) {
		this.id = id;
	}

	// Property accessors

	public EntitiesId getId() {
		return this.id;
	}

	public void setId(EntitiesId id) {
		this.id = id;
	}

}