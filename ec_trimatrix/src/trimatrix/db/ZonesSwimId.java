package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ZonesSwimId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ZonesSwimId implements java.io.Serializable {

	// Fields

	private Integer distance;
	private String athleteId;
	private String zonesDefinitionId;

	// Constructors

	/** default constructor */
	public ZonesSwimId() {
	}

	/** full constructor */
	public ZonesSwimId(Integer distance, String athleteId, String zonesDefinitionId) {
		this.distance = distance;
		this.athleteId = athleteId;
		this.zonesDefinitionId = zonesDefinitionId;
	}

	// Property accessors

	@Column(name = "distance", nullable = false)
	public Integer getDistance() {
		return this.distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Column(name = "athlete_id", nullable = false, length = 36)
	public String getAthleteId() {
		return this.athleteId;
	}

	public void setAthleteId(String athleteId) {
		this.athleteId = athleteId;
	}

	@Column(name = "zones_definition_id", nullable = false, length = 36)
	public String getZonesDefinitionId() {
		return this.zonesDefinitionId;
	}

	public void setZonesDefinitionId(String zonesDefinitionId) {
		this.zonesDefinitionId = zonesDefinitionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZonesSwimId))
			return false;
		ZonesSwimId castOther = (ZonesSwimId) other;

		return ((this.getDistance() == castOther.getDistance()) || (this.getDistance() != null && castOther.getDistance() != null && this.getDistance().equals(castOther.getDistance())))
				&& ((this.getAthleteId() == castOther.getAthleteId()) || (this.getAthleteId() != null && castOther.getAthleteId() != null && this.getAthleteId().equals(castOther.getAthleteId())))
				&& ((this.getZonesDefinitionId() == castOther.getZonesDefinitionId()) || (this.getZonesDefinitionId() != null && castOther.getZonesDefinitionId() != null && this
						.getZonesDefinitionId().equals(castOther.getZonesDefinitionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDistance() == null ? 0 : this.getDistance().hashCode());
		result = 37 * result + (getAthleteId() == null ? 0 : this.getAthleteId().hashCode());
		result = 37 * result + (getZonesDefinitionId() == null ? 0 : this.getZonesDefinitionId().hashCode());
		return result;
	}

}