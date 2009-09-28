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
	public TestsAnalysis(String id, Timestamp createdAt, String createdBy) {
		this.id = id;
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