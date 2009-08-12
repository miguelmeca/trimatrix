package trimatrix.db;

/**
 * PersonsAthlete entity. @author MyEclipse Persistence Tools
 */

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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getHeightUnit() {
		return this.heightUnit;
	}

	public void setHeightUnit(String heightUnit) {
		this.heightUnit = heightUnit;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightUnit() {
		return this.weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public Integer getMaxHr() {
		return this.maxHr;
	}

	public void setMaxHr(Integer maxHr) {
		this.maxHr = maxHr;
	}

	public Integer getRestingHr() {
		return this.restingHr;
	}

	public void setRestingHr(Integer restingHr) {
		this.restingHr = restingHr;
	}

	public Integer getVo2Max() {
		return this.vo2Max;
	}

	public void setVo2Max(Integer vo2Max) {
		this.vo2Max = vo2Max;
	}

}