package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SchedulesDetailId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SchedulesDetailId implements java.io.Serializable {

	// Fields

	private String id;
	private Integer sequence;

	// Constructors

	/** default constructor */
	public SchedulesDetailId() {
	}

	/** full constructor */
	public SchedulesDetailId(String id, Integer sequence) {
		this.id = id;
		this.sequence = sequence;
	}

	// Property accessors

	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "sequence", nullable = false)
	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SchedulesDetailId))
			return false;
		SchedulesDetailId castOther = (SchedulesDetailId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getSequence() == castOther.getSequence()) || (this.getSequence() != null && castOther.getSequence() != null && this.getSequence().equals(castOther.getSequence())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getSequence() == null ? 0 : this.getSequence().hashCode());
		return result;
	}

}