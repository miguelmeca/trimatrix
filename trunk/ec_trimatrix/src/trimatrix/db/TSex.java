package trimatrix.db;

/**
 * TSex entity. @author MyEclipse Persistence Tools
 */

public class TSex implements java.io.Serializable {

	// Fields

	private TSexId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TSex() {
	}

	/** minimal constructor */
	public TSex(TSexId id) {
		this.id = id;
	}

	/** full constructor */
	public TSex(TSexId id, String description, String descriptionLong) {
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