package trimatrix.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import trimatrix.entities.IEntityObject;

/**
 * Schedules entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schedules", catalog = "trimatrix")
public class Schedules implements java.io.Serializable, IEntityObject {

	// Fields

	private String id;
	private String personId;
	private String type;
	private String description;
	private Timestamp start;
	private Long duration;
	private String color;
	private Boolean template;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;

	// Constructors

	/** default constructor */
	public Schedules() {
	}

	/** minimal constructor */
	public Schedules(String id) {
		this.id = id;
	}

	/** full constructor */
	public Schedules(String id, String personId, String type, String description, Timestamp start, Long duration, String color, Boolean template, Timestamp createdAt, String createdBy, Timestamp modifiedAt,
			String modifiedBy, Boolean deleted, Boolean test) {
		this.id = id;
		this.personId = personId;
		this.type = type;
		this.description = description;
		this.start = start;
		this.duration = duration;
		this.color = color;
		this.template = template;
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

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description", columnDefinition="text")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "start", length = 19)
	public Timestamp getStart() {
		return this.start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	@Column(name = "duration")
	public Long getDuration() {
		return this.duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Column(name = "color", length = 10)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}
		
	@Column(name = "template")
	public Boolean getTemplate() {
		return template;
	}

	public void setTemplate(Boolean template) {
		this.template = template;
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

}