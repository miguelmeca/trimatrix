package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import trimatrix.relations.IRelationObject;

/**
 * PersonsHaveAttachments entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "persons_have_attachments", catalog = "trimatrix", uniqueConstraints = @UniqueConstraint(columnNames = { "person", "attachment", "reltyp_key" }))
public class PersonsHaveAttachments implements java.io.Serializable, IRelationObject {

	// Fields

	private String id;
	private String person;
	private String attachment;
	private String reltypKey;
	private Boolean standard;

	// Constructors

	/** default constructor */
	public PersonsHaveAttachments() {
	}

	/** minimal constructor */
	public PersonsHaveAttachments(String id) {
		this.id = id;
	}

	/** full constructor */
	public PersonsHaveAttachments(String id, String person, String attachment, String reltypKey, Boolean standard) {
		this.id = id;
		this.person = person;
		this.attachment = attachment;
		this.reltypKey = reltypKey;
		this.standard = standard;
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

	@Column(name = "person", length = 36)
	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "attachment", length = 36)
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Column(name = "reltyp_key", length = 10)
	public String getReltypKey() {
		return this.reltypKey;
	}

	public void setReltypKey(String reltypKey) {
		this.reltypKey = reltypKey;
	}

	@Column(name = "standard")
	public Boolean getStandard() {
		return this.standard;
	}

	public void setStandard(Boolean standard) {
		this.standard = standard;
	}

	@Transient
	public String getPartner1() {
		return getPerson();
	}

	@Transient
	public String getPartner2() {
		return getAttachment();
	}
	
	public void setPartner1(String id) {
		setPerson(id);
	}

	public void setPartner2(String id) {
		setAttachment(id);
	}

	@Override
	public String toString() {
		// same as DB relation implementation
		return (getPerson() + " " + getReltypKey() + " " + getAttachment());
	}
	
}