package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Zones entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zones", catalog = "trimatrix")
public class Zones implements java.io.Serializable {

	// Fields

	private String id;
	private String athleteId;
	private Double speedLow;
	private Double speedHigh;
	private Double powerLow;
	private Double powerHigh;
	private Boolean auto;
	private String testId;

	// Constructors

	/** default constructor */
	public Zones() {
	}

	/** minimal constructor */
	public Zones(String id, String athleteId) {
		this.id = id;
		this.athleteId = athleteId;
	}

	/** full constructor */
	public Zones(String id, String athleteId, Double speedLow,
			Double speedHigh, Double powerLow, Double powerHigh, Boolean auto,
			String testId) {
		this.id = id;
		this.athleteId = athleteId;
		this.speedLow = speedLow;
		this.speedHigh = speedHigh;
		this.powerLow = powerLow;
		this.powerHigh = powerHigh;
		this.auto = auto;
		this.testId = testId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "athlete_id", nullable = false, length = 36)
	public String getAthleteId() {
		return this.athleteId;
	}

	public void setAthleteId(String athleteId) {
		this.athleteId = athleteId;
	}

	@Column(name = "speed_low", columnDefinition="decimal")
	public Double getSpeedLow() {
		return this.speedLow;
	}

	public void setSpeedLow(Double speedLow) {
		this.speedLow = speedLow;
	}

	@Column(name = "speed_high", columnDefinition="decimal")
	public Double getSpeedHigh() {
		return this.speedHigh;
	}

	public void setSpeedHigh(Double speedHigh) {
		this.speedHigh = speedHigh;
	}

	@Column(name = "power_low", columnDefinition="decimal")
	public Double getPowerLow() {
		return this.powerLow;
	}

	public void setPowerLow(Double powerLow) {
		this.powerLow = powerLow;
	}

	@Column(name = "power_high", columnDefinition="decimal")
	public Double getPowerHigh() {
		return this.powerHigh;
	}

	public void setPowerHigh(Double powerHigh) {
		this.powerHigh = powerHigh;
	}

	@Column(name = "auto")
	public Boolean getAuto() {
		return this.auto;
	}

	public void setAuto(Boolean auto) {
		this.auto = auto;
	}

	@Column(name = "test_id", length = 36)
	public String getTestId() {
		return this.testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

}