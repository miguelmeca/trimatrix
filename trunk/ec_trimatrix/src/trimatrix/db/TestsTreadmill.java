package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TestsTreadmill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tests_treadmill", catalog = "trimatrix")
public class TestsTreadmill implements java.io.Serializable {

	// Fields

	private String id;
	private Boolean speedVariable;
	private Boolean inclineVariable;
	private Double speedInit;
	private Double speedStep;
	private Double inclineInit;
	private Double inclineStep;
	private Integer stepTime;

	// Constructors

	/** default constructor */
	public TestsTreadmill() {
	}

	/** minimal constructor */
	public TestsTreadmill(String id) {
		this.id = id;
	}

	/** full constructor */
	public TestsTreadmill(String id, Boolean speedVariable,
			Boolean inclineVariable, Double speedInit, Double speedStep,
			Double inclineInit, Double inclineStep, Integer stepTime) {
		this.id = id;
		this.speedVariable = speedVariable;
		this.inclineVariable = inclineVariable;
		this.speedInit = speedInit;
		this.speedStep = speedStep;
		this.inclineInit = inclineInit;
		this.inclineStep = inclineStep;
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

	@Column(name = "speed_variable")
	public Boolean getSpeedVariable() {
		return this.speedVariable;
	}

	public void setSpeedVariable(Boolean speedVariable) {
		this.speedVariable = speedVariable;
	}

	@Column(name = "incline_variable")
	public Boolean getInclineVariable() {
		return this.inclineVariable;
	}

	public void setInclineVariable(Boolean inclineVariable) {
		this.inclineVariable = inclineVariable;
	}

	@Column(name = "speed_init", precision = 2, scale = 1)
	public Double getSpeedInit() {
		return this.speedInit;
	}

	public void setSpeedInit(Double speedInit) {
		this.speedInit = speedInit;
	}

	@Column(name = "speed_step", precision = 2, scale = 1)
	public Double getSpeedStep() {
		return this.speedStep;
	}

	public void setSpeedStep(Double speedStep) {
		this.speedStep = speedStep;
	}

	@Column(name = "incline_init", precision = 2, scale = 1)
	public Double getInclineInit() {
		return this.inclineInit;
	}

	public void setInclineInit(Double inclineInit) {
		this.inclineInit = inclineInit;
	}

	@Column(name = "incline_step", precision = 2, scale = 1)
	public Double getInclineStep() {
		return this.inclineStep;
	}

	public void setInclineStep(Double inclineStep) {
		this.inclineStep = inclineStep;
	}

	@Column(name = "step_time")
	public Integer getStepTime() {
		return this.stepTime;
	}

	public void setStepTime(Integer stepTime) {
		this.stepTime = stepTime;
	}

}