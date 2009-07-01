package trimatrix.db;

/**
 * KFunctionnodes entity. @author MyEclipse Persistence Tools
 */

public class KFunctionnodes implements java.io.Serializable {

	// Fields

	private String key;
	private String page;
	private String entity;
	private Boolean edit;
	private Boolean create;
	private Boolean delete;

	// Constructors

	/** default constructor */
	public KFunctionnodes() {
	}

	/** minimal constructor */
	public KFunctionnodes(String key) {
		this.key = key;
	}

	/** full constructor */
	public KFunctionnodes(String key, String page, String entity, Boolean edit,
			Boolean create, Boolean delete) {
		this.key = key;
		this.page = page;
		this.entity = entity;
		this.edit = edit;
		this.create = create;
		this.delete = delete;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Boolean getEdit() {
		return this.edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public Boolean getCreate() {
		return this.create;
	}

	public void setCreate(Boolean create) {
		this.create = create;
	}

	public Boolean getDelete() {
		return this.delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

}