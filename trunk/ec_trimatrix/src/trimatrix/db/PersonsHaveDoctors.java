package trimatrix.db;

import trimatrix.relations.IRelationObject;



/**
 * PersonsHaveDoctors entity. @author MyEclipse Persistence Tools
 */
public class PersonsHaveDoctors extends AbstractPersonsHaveDoctors implements java.io.Serializable, IRelationObject {

    // Constructors

    /** default constructor */
    public PersonsHaveDoctors() {
    }

	/** minimal constructor */
    public PersonsHaveDoctors(String id) {
        super(id);        
    }
    
    /** full constructor */
    public PersonsHaveDoctors(String id, String person, String doctor, String reltypKey, Boolean standard) {
        super(id, person, doctor, reltypKey, standard);        
    }
    
    @Override
	public String toString() {
		// same as DB relation implementation
		return (getPerson() + " " + getReltypKey() + " " + getDoctor());
	}

	public String getPartner1() {
		return getPerson();
	}

	public String getPartner2() {
		return getDoctor();
	}
   
}
