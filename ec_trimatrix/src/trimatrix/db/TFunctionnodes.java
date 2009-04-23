package trimatrix.db;

/**
 * TFunctionnodes entity. @author MyEclipse Persistence Tools
 */
public class TFunctionnodes extends AbstractTFunctionnodes implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TFunctionnodes() {
	}

	/** minimal constructor */
	public TFunctionnodes(TFunctionnodesId id) {
		super(id);
	}

	/** full constructor */
	public TFunctionnodes(TFunctionnodesId id, String description,
			String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
