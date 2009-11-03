package trimatrix.db;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import trimatrix.entities.IEntityObject;

/**
 * Competitions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "competitions", catalog = "trimatrix")
public class Competitions implements java.io.Serializable, IEntityObject{

	// Fields

	private String id;
	private Date date;
	private String description;
	private String type;
	private String address;
	private String countryKey;
	private Boolean swimsuit;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean test;
	private Boolean deleted;

	// Constructors

	/** default constructor */
	public Competitions() {
	}

	/** minimal constructor */
	public Competitions(String id) {
		this.id = id;
	}

	/** full constructor */
	public Competitions(String id, Date date, String description, String type,
			String address, String countryKey, Boolean swimsuit,
			Timestamp createdAt, String createdBy, Timestamp modifiedAt,
			String modifiedBy, Boolean test, Boolean deleted) {
		this.id = id;
		this.date = date;
		this.description = description;
		this.type = type;
		this.address = address;
		this.countryKey = countryKey;
		this.swimsuit = swimsuit;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
		this.test = test;
		this.deleted = deleted;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "country_key", length = 2)
	public String getCountryKey() {
		return this.countryKey;
	}

	public void setCountryKey(String countryKey) {
		this.countryKey = countryKey;
	}

	@Column(name = "swimsuit")
	public Boolean getSwimsuit() {
		return this.swimsuit;
	}

	public void setSwimsuit(Boolean swimsuit) {
		this.swimsuit = swimsuit;
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

	@Column(name = "test")
	public Boolean getTest() {
		return this.test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		// same as DB entity implementation
		return description;
	}
}