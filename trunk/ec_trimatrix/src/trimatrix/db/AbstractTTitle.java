package trimatrix.db;

/**
 * AbstractTTitle entity provides the base persistence definition of the TTitle
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTTitle implements java.io.Serializable {

	// Fields

	private String key;
	private String languageKey;
	private String description;
	private String descriptionLong;

	// Constructors

	/** default constructor */
	public AbstractTTitle() {
	}

	/** minimal constructor */
	public AbstractTTitle(String key) {
		this.key = key;
	}

	/** full constructor */
	public AbstractTTitle(String key, String languageKey, String description,
			String descriptionLong) {
		this.key = key;
		this.languageKey = languageKey;
		this.description = description;
		this.descriptionLong = descriptionLong;
	}

	// Property accessors

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLanguageKey() {
		return this.languageKey;
	}

	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
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