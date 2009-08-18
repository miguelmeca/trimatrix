package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TestsProtocol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tests_protocol", catalog = "trimatrix")
public class TestsProtocol implements java.io.Serializable {

	// Fields

	private String id;
	private String description;
	private String model;
	private String modelLactate;
	private String modelSpiro;
	private Integer countSteps;
	private String lactate;
	private String hr;
	private String o2Absorption;
	private String co2Emission;
	private String rq;

	// Constructors

	/** default constructor */
	public TestsProtocol() {
	}

	/** minimal constructor */
	public TestsProtocol(String id) {
		this.id = id;
	}

	/** full constructor */
	public TestsProtocol(String id, String description, String model,
			String modelLactate, String modelSpiro, Integer countSteps,
			String lactate, String hr, String o2Absorption, String co2Emission,
			String rq) {
		this.id = id;
		this.description = description;
		this.model = model;
		this.modelLactate = modelLactate;
		this.modelSpiro = modelSpiro;
		this.countSteps = countSteps;
		this.lactate = lactate;
		this.hr = hr;
		this.o2Absorption = o2Absorption;
		this.co2Emission = co2Emission;
		this.rq = rq;
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

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "model", length = 50)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "model_lactate", length = 50)
	public String getModelLactate() {
		return this.modelLactate;
	}

	public void setModelLactate(String modelLactate) {
		this.modelLactate = modelLactate;
	}

	@Column(name = "model_spiro", length = 50)
	public String getModelSpiro() {
		return this.modelSpiro;
	}

	public void setModelSpiro(String modelSpiro) {
		this.modelSpiro = modelSpiro;
	}

	@Column(name = "count_steps")
	public Integer getCountSteps() {
		return this.countSteps;
	}

	public void setCountSteps(Integer countSteps) {
		this.countSteps = countSteps;
	}

	@Column(name = "lactate", length = 65535)
	public String getLactate() {
		return this.lactate;
	}

	public void setLactate(String lactate) {
		this.lactate = lactate;
	}

	@Column(name = "hr", length = 65535)
	public String getHr() {
		return this.hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	@Column(name = "o2_absorption", length = 65535)
	public String getO2Absorption() {
		return this.o2Absorption;
	}

	public void setO2Absorption(String o2Absorption) {
		this.o2Absorption = o2Absorption;
	}

	@Column(name = "co2_emission", length = 65535)
	public String getCo2Emission() {
		return this.co2Emission;
	}

	public void setCo2Emission(String co2Emission) {
		this.co2Emission = co2Emission;
	}

	@Column(name = "rq", length = 65535)
	public String getRq() {
		return this.rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

}