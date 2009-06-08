package trimatrix.db;

import trimatrix.relations.IRelationObject;

/**
 * PersonsHaveAttachments entity. @author MyEclipse Persistence Tools
 */
public class PersonsHaveAttachments extends AbstractPersonsHaveAttachments implements java.io.Serializable, IRelationObject {
	// Constructors

	/** default constructor */
	public PersonsHaveAttachments() {
	}

	/** minimal constructor */
	public PersonsHaveAttachments(String id) {
		super(id);
	}

	/** full constructor */
	public PersonsHaveAttachments(String id, String person, String attachment,
			String reltypKey, Boolean standard) {
		super(id, person, attachment, reltypKey, standard);
	}

	@Override
	public String toString() {
		// same as DB relation implementation
		return (getPerson() + " " + getReltypKey() + " " + getAttachment());
	}

	public String getPartner1() {
		return getPerson();
	}

	public String getPartner2() {
		return getAttachment();
	}
	
	public void setPartner1(String id) {
		setPerson(id);
	}

	public void setPartner2(String id) {
		setAttachment(id);
	}

}
