package trimatrix.db;

/**
 * TFunctionnodes entity. @author MyEclipse Persistence Tools
 */

public class TFunctionnodes implements java.io.Serializable {

	// Fields

	private TFunctionnodesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TFunctionnodes() {
	}

	/** minimal constructor */
	public TFunctionnodes(TFunctionnodesId id) {
		this.id = id;
	}

	/** full constructor */
	public TFunctionnodes(TFunctionnodesId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TFunctionnodesId getId() {
		return this.id;
	}

	public void setId(TFunctionnodesId id) {
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