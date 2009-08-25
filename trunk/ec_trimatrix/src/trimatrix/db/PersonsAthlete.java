package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersonsAthlete entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "persons_athlete", catalog = "trimatrix")
public class PersonsAthlete implements java.io.Serializable {

	// Fields

	private String id;
	private Double height;
	private String heightUnit;
	private Double weight;
	private String weightUnit;
	private Integer maxHr;
	private Integer restingHr;
	private Integer vo2Max;

	// Constructors

	/** default constructor */
	public PersonsAthlete() {
	}

	/** minimal constructor */
	public PersonsAthlete(String id) {
		this.id = id;
	}

	/** full constructor */
	public PersonsAthlete(String id, Double height, String heightUnit,
			Double weight, String weightUnit, Integer maxHr, Integer restingHr,
			Integer vo2Max) {
		this.id = id;
		this.height = height;
		this.heightUnit = heightUnit;
		this.weight = weight;
		this.weightUnit = weightUnit;
		this.maxHr = maxHr;
		this.restingHr = restingHr;
		this.vo2Max = vo2Max;
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

	@Column(name = "height", columnDefinition="decimal")
	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Column(name = "height_unit", length = 5)
	public String getHeightUnit() {
		return this.heightUnit;
	}

	public void setHeightUnit(String heightUnit) {
		this.heightUnit = heightUnit;
	}

	@Column(name = "weight", columnDefinition="decimal")
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "weight_unit", length = 5)
	public String getWeightUnit() {
		return this.weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	@Column(name = "max_hr")
	public Integer getMaxHr() {
		return this.maxHr;
	}

	public void setMaxHr(Integer maxHr) {
		this.maxHr = maxHr;
	}

	@Column(name = "resting_hr")
	public Integer getRestingHr() {
		return this.restingHr;
	}

	public void setRestingHr(Integer restingHr) {
		this.restingHr = restingHr;
	}

	@Column(name = "vo2_max")
	public Integer getVo2Max() {
		return this.vo2Max;
	}

	public void setVo2Max(Integer vo2Max) {
		this.vo2Max = vo2Max;
	}

}