package trimatrix.db;

/**
 * EntitiesHaveLabels entity. @author MyEclipse Persistence Tools
 */

public class EntitiesHaveLabels implements java.io.Serializable {

	// Fields

	private Integer id;
	private String entity;
	private String label;
	private String personId;

	// Constructors

	/** default constructor */
	public EntitiesHaveLabels() {
	}

	/** full constructor */
	public EntitiesHaveLabels(Integer id, String entity, String label,
			String personId) {
		this.id = id;
		this.entity = entity;
		this.label = label;
		this.personId = personId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}