package trimatrix.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TestsAnalysis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tests_analysis", catalog = "trimatrix")
public class TestsAnalysis implements java.io.Serializable {

	// Fields

	private String id;
	private String function;
	private Double offset;
	private Integer degree;
	private Boolean lactateHr;
	private Timestamp createdAt;
	private String createdBy;

	// Constructors

	/** default constructor */
	public TestsAnalysis() {
	}

	/** minimal constructor */
	public TestsAnalysis(String id) {
		this.id = id;
	}

	/** full constructor */
	public TestsAnalysis(String id, String function, Double offset, Integer degree, Boolean lactateHr, Timestamp createdAt, String createdBy) {
		super();
		this.id = id;
		this.function = function;
		this.offset = offset;
		this.degree = degree;
		this.lactateHr = lactateHr;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
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

	@Column(name="function", length=10)
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Column(name = "offset", columnDefinition="decimal")
	public Double getOffset() {
		return offset;
	}

	public void setOffset(Double offset) {
		this.offset = offset;
	}

	@Column(name="degree")
	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	@Column(name="lactate_hr")

    public Boolean getLactateHr() {
        return this.lactateHr;
    }

    public void setLactateHr(Boolean lactateHr) {
        this.lactateHr = lactateHr;
    }

	@Column(name = "created_at", length = 19)
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "created_by", length = 36)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}