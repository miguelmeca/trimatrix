package trimatrix.db;

/**
 * AbstractTSex entity provides the base persistence definition of the TSex
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTSex implements java.io.Serializable {

	// Fields

	private TSexId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTSex() {
	}

	/** minimal constructor */
	public AbstractTSex(TSexId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTSex(TSexId id, String description, String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TSexId getId() {
		return this.id;
	}

	public void setId(TSexId id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionLong() {
		return this.descriptionLong;
	}

	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}

}