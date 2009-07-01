package trimatrix.db;

/**
 * Labels entity. @author MyEclipse Persistence Tools
 */

public class Labels implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String description;
	private String color;

	// Constructors

	/** default constructor */
	public Labels() {
	}

	/** minimal constructor */
	public Labels(String id, String personId) {
		this.id = id;
		this.personId = personId;
	}

	/** full constructor */
	public Labels(String id, String personId, String description, String color) {
		this.id = id;
		this.personId = personId;
		this.description = description;
		this.color = color;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}