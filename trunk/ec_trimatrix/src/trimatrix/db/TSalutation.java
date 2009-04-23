package trimatrix.db;

/**
 * TSalutation entity. @author MyEclipse Persistence Tools
 */
public class TSalutation extends AbstractTSalutation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TSalutation() {
	}

	/** minimal constructor */
	public TSalutation(TSalutationId id) {
		super(id);
	}

	/** full constructor */
	public TSalutation(TSalutationId id, String description,
			String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
