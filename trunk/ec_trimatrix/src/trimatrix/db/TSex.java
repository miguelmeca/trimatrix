package trimatrix.db;

/**
 * TSex entity. @author MyEclipse Persistence Tools
 */
public class TSex extends AbstractTSex implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TSex() {
	}

	/** minimal constructor */
	public TSex(TSexId id) {
		super(id);
	}

	/** full constructor */
	public TSex(TSexId id, String description, String descriptionLong) {
		super(id, description, descriptionLong);
	}

}
