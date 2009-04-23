package trimatrix.db;

/**
 * TAuthorizations entity. @author MyEclipse Persistence Tools
 */
public class TAuthorizations extends AbstractTAuthorizations implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TAuthorizations() {
	}

	/** minimal constructor */
	public TAuthorizations(TAuthorizationsId id) {
		super(id);
	}

	/** full constructor */
	public TAuthorizations(TAuthorizationsId id, String description,
			String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
