package trimatrix.db;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import trimatrix.entities.IEntityObject;

/**
 * Tests entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tests", catalog = "trimatrix")
public class Tests implements java.io.Serializable, IEntityObject {

	// Fields
	private static final long serialVersionUID = -1388131704318568762L;
	private String id;
	private String personId;
	private String doctorId;
	private String type;
	private Timestamp date;
	private String description;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;
	
	// Relations
	private TestsErgo testsErgo;
	private TestsTreadmill testsTreadmill;
	private TestsProtocol testsProtocol;

	// Constructors

	/** default constructor */
	public Tests() {
	}

	/** minimal constructor */
	public Tests(String id) {
		this.id = id;
	}

	/** full constructor */
	public Tests(String id, String personId, String doctorId, String type,
			Timestamp date, String description, Timestamp createdAt,
			String createdBy, Timestamp modifiedAt, String modifiedBy,
			Boolean deleted, Boolean test) {
		this.id = id;
		this.personId = personId;
		this.doctorId = doctorId;
		this.type = type;
		this.date = date;
		this.description = description;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
		this.deleted = deleted;
		this.test = test;
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

	@Column(name = "person_id", length = 36)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "doctor_id", length = 36)
	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "date", length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Version
	@Column(name = "modified_at", length = 19)
	public Timestamp getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Column(name = "modified_by", length = 36)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Column(name = "test")
	public Boolean getTest() {
		return this.test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @LazyToOne(LazyToOneOption.FALSE)
	public TestsErgo getTestsErgo() {
		return testsErgo;
	}

	public void setTestsErgo(TestsErgo testsErgo) {
		this.testsErgo = testsErgo;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @LazyToOne(LazyToOneOption.FALSE)
	public TestsTreadmill getTestsTreadmill() {
		return testsTreadmill;
	}

	public void setTestsTreadmill(TestsTreadmill testsTreadmill) {
		this.testsTreadmill = testsTreadmill;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @LazyToOne(LazyToOneOption.FALSE)
	public TestsProtocol getTestsProtocol() {
		return testsProtocol;
	}

	public void setTestsProtocol(TestsProtocol testsProtocol) {
		this.testsProtocol = testsProtocol;
	}

	@Override
	public String toString() {
		// same as DB entity implementation
		return description;
	}
	
}