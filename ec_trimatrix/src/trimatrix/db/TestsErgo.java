package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TestsErgo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tests_ergo", catalog = "trimatrix")
public class TestsErgo implements java.io.Serializable {

	// Fields

	private String id;
	private Double powerInit;
	private Double powerStep;
	private Double cadenceLow;
	private Double cadenceHigh;
	private Integer stepTime;

	// Constructors

	/** default constructor */
	public TestsErgo() {
	}

	/** minimal constructor */
	public TestsErgo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TestsErgo(String id, Double powerInit, Double powerStep,
			Double cadenceLow, Double cadenceHigh, Integer stepTime) {
		this.id = id;
		this.powerInit = powerInit;
		this.powerStep = powerStep;
		this.cadenceLow = cadenceLow;
		this.cadenceHigh = cadenceHigh;
		this.stepTime = stepTime;
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

	@Column(name = "power_init", precision = 4, scale = 1)
	public Double getPowerInit() {
		return this.powerInit;
	}

	public void setPowerInit(Double powerInit) {
		this.powerInit = powerInit;
	}

	@Column(name = "power_step", precision = 4, scale = 1)
	public Double getPowerStep() {
		return this.powerStep;
	}

	public void setPowerStep(Double powerStep) {
		this.powerStep = powerStep;
	}

	@Column(name = "cadence_low", precision = 4, scale = 1)
	public Double getCadenceLow() {
		return this.cadenceLow;
	}

	public void setCadenceLow(Double cadenceLow) {
		this.cadenceLow = cadenceLow;
	}

	@Column(name = "cadence_high", precision = 4, scale = 1)
	public Double getCadenceHigh() {
		return this.cadenceHigh;
	}

	public void setCadenceHigh(Double cadenceHigh) {
		this.cadenceHigh = cadenceHigh;
	}

	@Column(name = "step_time")
	public Integer getStepTime() {
		return this.stepTime;
	}

	public void setStepTime(Integer stepTime) {
		this.stepTime = stepTime;
	}

}