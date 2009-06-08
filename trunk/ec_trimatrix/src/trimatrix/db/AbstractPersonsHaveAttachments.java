package trimatrix.db;

/**
 * AbstractPersonsHaveAttachments entity provides the base persistence
 * definition of the PersonsHaveAttachments entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractPersonsHaveAttachments implements
		java.io.Serializable {

	// Fields

	private String id;
	private String person;
	private String attachment;
	private String reltypKey;
	private Boolean standard;

	// Constructors

	/** default constructor */
	public AbstractPersonsHaveAttachments() {
	}

	/** minimal constructor */
	public AbstractPersonsHaveAttachments(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractPersonsHaveAttachments(String id, String person,
			String attachment, String reltypKey, Boolean standard) {
		this.id = id;
		this.person = person;
		this.attachment = attachment;
		this.reltypKey = reltypKey;
		this.standard = standard;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getReltypKey() {
		return this.reltypKey;
	}

	public void setReltypKey(String reltypKey) {
		this.reltypKey = reltypKey;
	}

	public Boolean getStandard() {
		return this.standard;
	}

	public void setStandard(Boolean standard) {
		this.standard = standard;
	}

}