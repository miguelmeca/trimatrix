package trimatrix.db;

import trimatrix.relations.IRelationObject;

/**
 * PersonsHaveRelations entity. @author MyEclipse Persistence Tools
 */
public class PersonsHaveRelations extends AbstractPersonsHaveRelations 
		implements java.io.Serializable, IRelationObject {

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
			String reltypKey, Boolean standard) {
		super(id, partner1, partner2, reltypKey, standard);
	}
	
	@Override
	public String toString() {
		// same as DB relation implementation
		return (getPartner1() + " " + getReltypKey() + " " + getPartner2());
	}
}