package trimatrix.db;

/**
 * TLanguages entity. @author MyEclipse Persistence Tools
 */
public class TLanguages extends AbstractTLanguages implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TLanguages() {
	}

	/** minimal constructor */
	public TLanguages(TLanguagesId id) {
		super(id);
	}

	/** full constructor */
	public TLanguages(TLanguagesId id, String description,
			String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
