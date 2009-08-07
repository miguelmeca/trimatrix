package trimatrix.db;

/**
 * EntitiesId entity. @author MyEclipse Persistence Tools
 */

public class EntitiesId implements java.io.Serializable {

	// Fields

	private String id;
	private String entity;
	private Short deleted;

	// Constructors

	/** default constructor */
	public EntitiesId() {
	}

	/** minimal constructor */
	public EntitiesId(String id, String entity) {
		this.id = id;
		this.entity = entity;
	}

	/** full constructor */
	public EntitiesId(String id, String entity, Short deleted) {
		this.id = id;
		this.entity = entity;
		this.deleted = deleted;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Short getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Short deleted) {
		this.deleted = deleted;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntitiesId))
			return false;
		EntitiesId castOther = (EntitiesId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getEntity() == castOther.getEntity()) || (this
						.getEntity() != null
						&& castOther.getEntity() != null && this.getEntity()
						.equals(castOther.getEntity())))
				&& ((this.getDeleted() == castOther.getDeleted()) || (this
						.getDeleted() != null
						&& castOther.getDeleted() != null && this.getDeleted()
						.equals(castOther.getDeleted())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getEntity() == null ? 0 : this.getEntity().hashCode());
		result = 37 * result
				+ (getDeleted() == null ? 0 : this.getDeleted().hashCode());
		return result;
	}

}