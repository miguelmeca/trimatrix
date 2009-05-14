package trimatrix.db;

/**
 * AbstractPersonsHaveRelations entity provides the base persistence definition
 * of the PersonsHaveRelations entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPersonsHaveRelations implements
		java.io.Serializable {

	// Fields

	private String id;
	private String partner1;
	private String partner2;
	private String reltypKey;
	private Boolean standard;

	// Constructors

	/** default constructor */
	public AbstractPersonsHaveRelations() {
	}

	/** minimal constructor */
	public AbstractPersonsHaveRelations(String id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractPersonsHaveRelations(String id, String partner1,
			String partner2, String reltypKey, Boolean default_) {
		this.id = id;
		this.partner1 = partner1;
		this.partner2 = partner2;
		this.reltypKey = reltypKey;
		this.standard = default_;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartner1() {
		return this.partner1;
	}

	public void setPartner1(String partner1) {
		this.partner1 = partner1;
	}

	public String getPartner2() {
		return this.partner2;
	}

	public void setPartner2(String partner2) {
		this.partner2 = partner2;
	}

	public String getReltypKey() {
		return this.reltypKey;
	}

	public void setReltypKey(String reltypKey) {
		this.reltypKey = reltypKey;
	}

	public Boolean getStandard() {
		return standard;
	}

	public void setStandard(Boolean standard) {
		this.standard = standard;
	}
}