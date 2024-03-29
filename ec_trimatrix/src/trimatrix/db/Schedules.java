package trimatrix.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.OrderBy;

import trimatrix.entities.IEntityObject;
import trimatrix.utils.TimestampAdapter;

/**
 * Schedules entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schedules", catalog = "trimatrix")
@XmlRootElement

public class Schedules implements java.io.Serializable, IEntityObject {

	// Fields

	private String id;
	private String personId;
	private String type;
	private String description;
	private String comment;
	private Timestamp start;
	private Long duration;
	private String color;
	private Boolean template;
	private String templateName;
	private Boolean done;
	private Timestamp createdAt;
	private String createdBy;
	private Timestamp modifiedAt;
	private String modifiedBy;
	private Boolean deleted;
	private Boolean test;

	// Collections
    private List<SchedulesDetail> schedulesDetail = new ArrayList<SchedulesDetail>();

	// Constructors

	/** default constructor */
	public Schedules() {
	}

	/** minimal constructor */
	public Schedules(String id) {
		this.id = id;
	}

	/** full constructor */
	public Schedules(String id, String personId, String type, String description, String comment, Timestamp start, Long duration, String color, Boolean template, String templateName,
			Boolean done, Timestamp createdAt, String createdBy, Timestamp modifiedAt, String modifiedBy, Boolean deleted, Boolean test) {
		this.id = id;
		this.personId = personId;
		this.type = type;
		this.description = description;
		this.comment = comment;
		this.start = start;
		this.duration = duration;
		this.color = color;
		this.template = template;
		this.templateName = templateName;
		this.done = done;
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

	@Column(name = "comment", columnDefinition="text")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "start", length = 19)
	@XmlJavaTypeAdapter( TimestampAdapter.class)
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

	@Column(name = "template_name", length = 100)
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "done")
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Column(name = "created_at", length = 19)
	@XmlJavaTypeAdapter( TimestampAdapter.class)
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
	@Version
	@XmlJavaTypeAdapter( TimestampAdapter.class)
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

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "id.id")
    @OrderBy(clause="sequence")
	public List<SchedulesDetail> getSchedulesDetail() {
		return schedulesDetail;
	}

	public void setSchedulesDetail(List<SchedulesDetail> schedulesDetail) {
		this.schedulesDetail = schedulesDetail;
	}

	@Transient
	public Timestamp getEnd() {
		return new Timestamp(getStart().getTime() + getDuration() * 60000);
	}

}