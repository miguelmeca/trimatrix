package trimatrix.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private String zonesDefinitionId;
	private Integer hrLow;
	private Integer hrHigh;
	private Double speedLow;
	private Double speedHigh;
	private Boolean autoHr;
	private Boolean autoSpeed;
	private String testIdHr;
	private String testIdSpeed;
	private ZonesDefinition zonesDefinition;

	// Constructors

	/** default constructor */
	public Zones() {
	}

	/** minimal constructor */
	public Zones(String id, String athleteId, String zonesDefinitionId) {
		this.id = id;
		this.athleteId = athleteId;
		this.zonesDefinitionId = zonesDefinitionId;
	}

	/** full constructor */
	public Zones(String id, String athleteId, String zonesDefinitionId,
			Integer hrLow, Integer hrHigh, Double speedLow, Double speedHigh,
			Boolean autoHr, Boolean autoSpeed, String testIdHr,
			String testIdSpeed) {
		this.id = id;
		this.athleteId = athleteId;
		this.zonesDefinitionId = zonesDefinitionId;
		this.hrLow = hrLow;
		this.hrHigh = hrHigh;
		this.speedLow = speedLow;
		this.speedHigh = speedHigh;
		this.autoHr = autoHr;
		this.autoSpeed = autoSpeed;
		this.testIdHr = testIdHr;
		this.testIdSpeed = testIdSpeed;
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

	@Column(name = "zones_definition_id", nullable = false, length = 36)
	public String getZonesDefinitionId() {
		return this.zonesDefinitionId;
	}

	public void setZonesDefinitionId(String zonesDefinitionId) {
		this.zonesDefinitionId = zonesDefinitionId;
	}

	@Column(name = "hr_low")
	public Integer getHrLow() {
		return this.hrLow;
	}

	public void setHrLow(Integer hrLow) {
		this.hrLow = hrLow;
	}

	@Column(name = "hr_high")
	public Integer getHrHigh() {
		return this.hrHigh;
	}

	public void setHrHigh(Integer hrHigh) {
		this.hrHigh = hrHigh;
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

	@Column(name = "auto_hr")
	public Boolean getAutoHr() {
		return this.autoHr;
	}

	public void setAutoHr(Boolean autoHr) {
		this.autoHr = autoHr;
	}

	@Column(name = "auto_speed")
	public Boolean getAutoSpeed() {
		return this.autoSpeed;
	}

	public void setAutoSpeed(Boolean autoSpeed) {
		this.autoSpeed = autoSpeed;
	}

	@Column(name = "test_id_hr", length = 36)
	public String getTestIdHr() {
		return this.testIdHr;
	}

	public void setTestIdHr(String testIdHr) {
		this.testIdHr = testIdHr;
	}

	@Column(name = "test_id_speed", length = 36)
	public String getTestIdSpeed() {
		return this.testIdSpeed;
	}

	public void setTestIdSpeed(String testIdSpeed) {
		this.testIdSpeed = testIdSpeed;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="zones_definition_id", insertable=false, updatable=false)
	public ZonesDefinition getZonesDefinition() {
		return zonesDefinition;
	}

	public void setZonesDefinition(ZonesDefinition zonesDefinition) {
		this.zonesDefinition = zonesDefinition;
	}
}