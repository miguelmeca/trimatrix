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
	private Integer powerInit;
	private Integer powerStep;
	private Integer cadenceLow;
	private Integer cadenceHigh;
	private String stepTime;

	// Constructors

	/** default constructor */
	public TestsErgo() {
	}

	/** minimal constructor */
	public TestsErgo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TestsErgo(String id, Integer powerInit, Integer powerStep,
			Integer cadenceLow, Integer cadenceHigh, String stepTime) {
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

	@Column(name = "power_init")
	public Integer getPowerInit() {
		return this.powerInit;
	}

	public void setPowerInit(Integer powerInit) {
		this.powerInit = powerInit;
	}

	@Column(name = "power_step")
	public Integer getPowerStep() {
		return this.powerStep;
	}

	public void setPowerStep(Integer powerStep) {
		this.powerStep = powerStep;
	}

	@Column(name = "cadence_low")
	public Integer getCadenceLow() {
		return this.cadenceLow;
	}

	public void setCadenceLow(Integer cadenceLow) {
		this.cadenceLow = cadenceLow;
	}

	@Column(name = "cadence_high")
	public Integer getCadenceHigh() {
		return this.cadenceHigh;
	}

	public void setCadenceHigh(Integer cadenceHigh) {
		this.cadenceHigh = cadenceHigh;
	}

	@Column(name = "step_time", length = 5)
	public String getStepTime() {
		return this.stepTime;
	}

	public void setStepTime(String stepTime) {
		this.stepTime = stepTime;
	}
}