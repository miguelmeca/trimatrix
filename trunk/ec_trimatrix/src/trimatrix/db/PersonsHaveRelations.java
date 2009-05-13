package trimatrix.db;

/**
 * PersonsHaveRelations entity. @author MyEclipse Persistence Tools
 */
public class PersonsHaveRelations extends AbstractPersonsHaveRelations
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PersonsHaveRelations() {
	}

	/** minimal constructor */
	public PersonsHaveRelations(String id) {
		super(id);
	}

	/** full constructor */
	public PersonsHaveRelations(String id, String partner1, String partner2,
			String reltypKey, Boolean default_) {
		super(id, partner1, partner2, reltypKey, default_);
	}

}
