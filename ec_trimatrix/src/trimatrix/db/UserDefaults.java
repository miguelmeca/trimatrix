package trimatrix.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserDefaults entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_defaults", catalog = "trimatrix")
public class UserDefaults implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String field;
	private String dependency;
	private String value;

	// Constructors

	/** default constructor */
	public UserDefaults() {
	}

	/** minimal constructor */
	public UserDefaults(String id, String userId, String field) {
		this.id = id;
		this.userId = userId;
		this.field = field;
	}

	/** full constructor */
	public UserDefaults(String id, String userId, String field, String dependency, String value) {
		this.id = id;
		this.userId = userId;
		this.field = field;
		this.dependency = dependency;
		this.value = value;
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

	@Column(name = "user_id", nullable = false, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "field", nullable = false, length = 10)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "dependency", length = 20)
	public String getDependency() {
		return this.dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	@Column(name = "value", length = 20)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}