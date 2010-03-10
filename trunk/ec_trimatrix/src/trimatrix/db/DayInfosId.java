package trimatrix.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DayInfosId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DayInfosId implements java.io.Serializable {

	// Fields

	private Date date;
	private String personId;

	// Constructors

	/** default constructor */
	public DayInfosId() {
	}

	/** full constructor */
	public DayInfosId(Date date, String personId) {
		this.date = date;
		this.personId = personId;
	}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "person_id", nullable = false, length = 36)
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
		if (!(other instanceof DayInfosId))
			return false;
		DayInfosId castOther = (DayInfosId) other;

		return ((this.getDate() == castOther.getDate()) || (this.getDate() != null
				&& castOther.getDate() != null && this.getDate().equals(
				castOther.getDate())))
				&& ((this.getPersonId() == castOther.getPersonId()) || (this
						.getPersonId() != null
						&& castOther.getPersonId() != null && this
						.getPersonId().equals(castOther.getPersonId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDate() == null ? 0 : this.getDate().hashCode());
		result = 37 * result
				+ (getPersonId() == null ? 0 : this.getPersonId().hashCode());
		return result;
	}

}