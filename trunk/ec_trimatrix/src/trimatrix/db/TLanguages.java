package trimatrix.db;

/**
 * TLanguages entity. @author MyEclipse Persistence Tools
 */

public class TLanguages implements java.io.Serializable {

	// Fields

	private TLanguagesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public TLanguages() {
	}

	/** minimal constructor */
	public TLanguages(TLanguagesId id) {
		this.id = id;
	}

	/** full constructor */
	public TLanguages(TLanguagesId id, String description,
			String descriptionLong) {
		this.id = id;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public TLanguagesId getId() {
		return this.id;
	}

	public void setId(TLanguagesId id) {
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