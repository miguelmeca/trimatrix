package trimatrix.db;

import java.sql.Timestamp;

import trimatrix.entities.IEntityObject;
import trimatrix.utils.Constants;

/**
 * Persons entity. @author MyEclipse Persistence Tools
 */
public class Persons extends AbstractPersons implements java.io.Serializable, IEntityObject {

	private PersonsAthlete profileAthlete;
	
	// Constructors

	/** default constructor */
	public Persons() {
	}

	/** full constructor */
	public Persons(String salutationKey, String nameFirst, String nameLast,
			String sexKey, String street, String housenumber, String postcode,
			String city, String state, String countryKey, String email,
			String homepage, String telephone, String mobile, String fax,
			Timestamp birthdate, byte[] picture, Timestamp createdAt,
			String createdBy, Timestamp modifiedAt, String modifiedBy,
			Boolean deleted, Boolean test) {
		super(salutationKey, nameFirst, nameLast, sexKey, street, housenumber,
				postcode, city, state, countryKey, email, homepage, telephone,
				mobile, fax, birthdate, picture, createdAt, createdBy,
				modifiedAt, modifiedBy, deleted, test);
	}
	
	@Override
	public String toString() {
		// same as DB entity implementation
		return (getNameFirst() + " " + getNameLast()).replace(Constants.NULL, Constants.EMPTY).trim();
	}

	public PersonsAthlete getProfileAthlete() {
		return profileAthlete;
	}

	public void setProfileAthlete(PersonsAthlete profileAthlete) {
		this.profileAthlete = profileAthlete;
	}

}
