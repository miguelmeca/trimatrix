package trimatrix.db;

/**
 * AbstractEntitiesHaveLabelsId entity provides the base persistence definition
 * of the EntitiesHaveLabelsId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractEntitiesHaveLabelsId implements
		java.io.Serializable {

	// Fields

	private String entity;
	private String label;
	private String personId;

	// Constructors

	/** default constructor */
	public AbstractEntitiesHaveLabelsId() {
	}

	/** full constructor */
	public AbstractEntitiesHaveLabelsId(String entity, String label,
			String personId) {
		this.entity = entity;
		this.label = label;
		this.personId = personId;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractEntitiesHaveLabelsId))
			return false;
		AbstractEntitiesHaveLabelsId castOther = (AbstractEntitiesHaveLabelsId) other;

		return ((this.getEntity() == castOther.getEntity()) || (this
				.getEntity() != null
				&& castOther.getEntity() != null && this.getEntity().equals(
				castOther.getEntity())))
				&& ((this.getLabel() == castOther.getLabel()) || (this
						.getLabel() != null
						&& castOther.getLabel() != null && this.getLabel()
						.equals(castOther.getLabel())))
				&& ((this.getPersonId() == castOther.getPersonId()) || (this
						.getPersonId() != null
						&& castOther.getPersonId() != null && this
						.getPersonId().equals(castOther.getPersonId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEntity() == null ? 0 : this.getEntity().hashCode());
		result = 37 * result
				+ (getLabel() == null ? 0 : this.getLabel().hashCode());
		result = 37 * result
				+ (getPersonId() == null ? 0 : this.getPersonId().hashCode());
		return result;
	}

}