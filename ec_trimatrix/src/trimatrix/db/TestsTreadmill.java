package trimatrix.db;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

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
	private Integer inclineInit;
	private Integer inclineStep;
	private String stepTime;

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
			Integer inclineInit, Integer inclineStep, String stepTime) {
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

	@Column(name = "speed_init", columnDefinition="decimal")
	public Double getSpeedInit() {
		return this.speedInit;
	}

	public void setSpeedInit(Double speedInit) {
		this.speedInit = speedInit;
	}

	@Column(name = "speed_step", columnDefinition="decimal")
	public Double getSpeedStep() {
		return this.speedStep;
	}

	public void setSpeedStep(Double speedStep) {
		this.speedStep = speedStep;
	}

	@Column(name = "incline_init")
	public Integer getInclineInit() {
		return this.inclineInit;
	}

	public void setInclineInit(Integer inclineInit) {
		this.inclineInit = inclineInit;
	}

	@Column(name = "incline_step")
	public Integer getInclineStep() {
		return this.inclineStep;
	}

	public void setInclineStep(Integer inclineStep) {
		this.inclineStep = inclineStep;
	}

	@Column(name = "step_time", length = 5)
	public String getStepTime() {
		return this.stepTime;
	}

	public void setStepTime(String stepTime) {
		this.stepTime = stepTime;
	}
}