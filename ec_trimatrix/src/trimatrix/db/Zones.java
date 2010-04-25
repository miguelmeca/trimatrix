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
	private Integer hrLowRun;
	private Integer hrHighRun;
	private Integer hrLowBike;
	private Integer hrHighBike;
	private Double speedLowSwim;
	private Double speedHighSwim;
	private Boolean autoHrRun;
	private Boolean autoHrBike;
	private Boolean autoSpeedSwim;
	private String testIdRun;
	private String testIdBike;
	private String testIdSwim;
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
			Integer hrLowRun, Integer hrHighRun,
			Integer hrLowBike, Integer hrHighBike,
			Double speedLowSwim, Double speedHighSwim,
			Boolean autoHrRun, Boolean autoHrBike, Boolean autoSpeedSwim,
			String testIdRun, String testIdBike, String testIdSwim) {
		this.id = id;
		this.athleteId = athleteId;
		this.zonesDefinitionId = zonesDefinitionId;
		this.hrLowRun = hrLowRun;
		this.hrHighRun = hrHighRun;
		this.hrLowBike = hrLowBike;
		this.hrHighBike = hrHighBike;
		this.speedLowSwim = speedLowSwim;
		this.speedHighSwim = speedHighSwim;
		this.autoHrRun = autoHrRun;
		this.autoHrBike = autoHrBike;
		this.autoSpeedSwim = autoSpeedSwim;
		this.testIdRun = testIdRun;
		this.testIdBike = testIdBike;
		this.testIdSwim = testIdSwim;
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

	@Column(name = "hr_low_run")
	public Integer getHrLowRun() {
		return this.hrLowRun;
	}

	public void setHrLowRun(Integer hrLowRun) {
		this.hrLowRun = hrLowRun;
	}

	@Column(name = "hr_high_run")
	public Integer getHrHighRun() {
		return this.hrHighRun;
	}

	public void setHrHighRun(Integer hrHighRun) {
		this.hrHighRun = hrHighRun;
	}

	@Column(name = "hr_low_bike")
	public Integer getHrLowBike() {
		return this.hrLowBike;
	}

	public void setHrLowBike(Integer hrLowBike) {
		this.hrLowBike = hrLowBike;
	}

	@Column(name = "hr_high_bike")
	public Integer getHrHighBike() {
		return this.hrHighBike;
	}

	public void setHrHighBike(Integer hrHighBike) {
		this.hrHighBike = hrHighBike;
	}

	@Column(name = "speed_low_swim", columnDefinition="decimal")
	public Double getSpeedLowSwim() {
		return this.speedLowSwim;
	}

	public void setSpeedLowSwim(Double speedLowSwim) {
		this.speedLowSwim = speedLowSwim;
	}

	@Column(name = "speed_high_swim", columnDefinition="decimal")
	public Double getSpeedHighSwim() {
		return this.speedHighSwim;
	}

	public void setSpeedHighSwim(Double speedHighSwim) {
		this.speedHighSwim = speedHighSwim;
	}

	@Column(name = "auto_hr_run")
	public Boolean getAutoHrRun() {
		return this.autoHrRun;
	}

	public void setAutoHrRun(Boolean autoHrRun) {
		this.autoHrRun = autoHrRun;
	}

	@Column(name = "auto_hr_bike")
	public Boolean getAutoHrBike() {
		return this.autoHrBike;
	}

	public void setAutoHrBike(Boolean autoHrBike) {
		this.autoHrBike = autoHrBike;
	}

	@Column(name = "auto_speed_swim")
	public Boolean getAutoSpeedSwim() {
		return this.autoSpeedSwim;
	}

	public void setAutoSpeedSwim(Boolean autoSpeedSwim) {
		this.autoSpeedSwim = autoSpeedSwim;
	}

	@Column(name = "test_id_run", length = 36)
	public String getTestIdRun() {
		return this.testIdRun;
	}

	public void setTestIdRun(String testIdRun) {
		this.testIdRun = testIdRun;
	}

	@Column(name = "test_id_bike", length = 36)
	public String getTestIdBike() {
		return this.testIdBike;
	}

	public void setTestIdBike(String testIdBike) {
		this.testIdBike = testIdBike;
	}

	@Column(name = "test_id_swim", length = 36)
	public String getTestIdSwim() {
		return this.testIdSwim;
	}

	public void setTestIdSwim(String testIdSwim) {
		this.testIdSwim = testIdSwim;
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