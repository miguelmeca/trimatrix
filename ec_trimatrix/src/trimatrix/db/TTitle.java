package trimatrix.db;

/**
 * TTitle entity. @author MyEclipse Persistence Tools
 */
public class TTitle extends AbstractTTitle implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TTitle() {
	}

	/** minimal constructor */
	public TTitle(String key) {
		super(key);
	}

	/** full constructor */
	public TTitle(String key, String languageKey, String description,
			String descriptionLong) {
		super(key, languageKey, description, descriptionLong);
	}

}
