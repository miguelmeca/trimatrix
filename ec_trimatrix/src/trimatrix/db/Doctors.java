package trimatrix.db;

import java.sql.Timestamp;

import trimatrix.entities.IEntityObject;
import trimatrix.utils.Constants;


/**
 * Doctors entity. @author MyEclipse Persistence Tools
 */
public class Doctors extends AbstractDoctors implements java.io.Serializable, IEntityObject {

    // Constructors

    /** default constructor */
    public Doctors() {
    }

	/** minimal constructor */
    public Doctors(String id) {
        super(id);        
    }
    
    /** full constructor */
    public Doctors(String id, String name, String street, String housenumber, String postcode, String city, String state, String countryKey, String email, String homepage, String telephone, String mobile, String fax, Timestamp createdAt, String createdBy, Timestamp modifiedAt, String modifiedBy, Boolean deleted, Boolean test) {
        super(id, name, street, housenumber, postcode, city, state, countryKey, email, homepage, telephone, mobile, fax, createdAt, createdBy, modifiedAt, modifiedBy, deleted, test);        
    }
    
    @Override
	public String toString() {
		// same as DB entity implementation
		return getName().replace(Constants.NULL, Constants.EMPTY).trim();
	}
   
}
