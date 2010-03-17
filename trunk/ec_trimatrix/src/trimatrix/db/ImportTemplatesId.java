package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ImportTemplatesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ImportTemplatesId implements java.io.Serializable {

	// Fields

	private String entity;
	private String personId;
	private String description;

	// Constructors

	/** default constructor */
	public ImportTemplatesId() {
	}

	/** full constructor */
	public ImportTemplatesId(String entity, String personId, String description) {
		this.entity = entity;
		this.personId = personId;
		this.description = description;
	}

	// Property accessors

	@Column(name = "entity", nullable = false, length = 25)
	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Column(name = "person_id", nullable = false, length = 36)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "description", nullable = false, length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ImportTemplatesId))
			return false;
		ImportTemplatesId castOther = (ImportTemplatesId) other;

		return ((this.getEntity() == castOther.getEntity()) || (this
				.getEntity() != null
				&& castOther.getEntity() != null && this.getEntity().equals(
				castOther.getEntity())))
				&& ((this.getPersonId() == castOther.getPersonId()) || (this
						.getPersonId() != null
						&& castOther.getPersonId() != null && this
						.getPersonId().equals(castOther.getPersonId())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEntity() == null ? 0 : this.getEntity().hashCode());
		result = 37 * result
				+ (getPersonId() == null ? 0 : this.getPersonId().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		return result;
	}

}