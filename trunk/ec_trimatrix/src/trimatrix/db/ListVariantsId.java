package trimatrix.db;

/**
 * ListVariantsId entity. @author MyEclipse Persistence Tools
 */

public class ListVariantsId implements java.io.Serializable {

	// Fields

	private String list;
	private String entity;
	private String userId;

	// Constructors

	/** default constructor */
	public ListVariantsId() {
	}

	/** full constructor */
	public ListVariantsId(String list, String entity, String userId) {
		this.list = list;
		this.entity = entity;
		this.userId = userId;
	}

	// Property accessors

	public String getList() {
		return this.list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ListVariantsId))
			return false;
		ListVariantsId castOther = (ListVariantsId) other;

		return ((this.getList() == castOther.getList()) || (this.getList() != null
				&& castOther.getList() != null && this.getList().equals(
				castOther.getList())))
				&& ((this.getEntity() == castOther.getEntity()) || (this
						.getEntity() != null
						&& castOther.getEntity() != null && this.getEntity()
						.equals(castOther.getEntity())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getList() == null ? 0 : this.getList().hashCode());
		result = 37 * result
				+ (getEntity() == null ? 0 : this.getEntity().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}