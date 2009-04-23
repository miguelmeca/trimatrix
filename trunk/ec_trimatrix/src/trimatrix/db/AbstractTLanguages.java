package trimatrix.db;

/**
 * AbstractTLanguages entity provides the base persistence definition of the
 * TLanguages entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTLanguages implements java.io.Serializable {

	// Fields

	private TLanguagesId id;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTLanguages() {
	}

	/** minimal constructor */
	public AbstractTLanguages(TLanguagesId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTLanguages(TLanguagesId id, String description,
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