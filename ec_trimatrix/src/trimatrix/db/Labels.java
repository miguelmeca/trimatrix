package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Labels entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "labels", catalog = "trimatrix")
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
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "person_id", nullable = false, length = 36)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "color", length = 10)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}