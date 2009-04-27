package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.IPersonsDAO;
import trimatrix.db.Persons;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public final class PersonEntity implements IEntity {
	// Constants
	public static final String NAME_FIRST = "name_first";
    public static final String NAME_LAST = "name_last";  
    public static final String EMAIL = "email";
    public static final String SEX = "sex";
    public static final String BIRTHDATE = "birthdate";
	
    // Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IPersonsDAO personsDAO;
		
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Vorname", NAME_FIRST, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Nachname", NAME_LAST, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Email", EMAIL, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Geschlecht", SEX, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Geburtstag", BIRTHDATE, SGridMetaData.Component.CALENDARFIELD));
        return gridMetaData;
    }
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData()
	 */
	public List<IEntityData> getData() {
		return sqlExecutorService.getPersonEntities();
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#delete(java.lang.String)
	 */
	public boolean delete(String id) {
		Persons person = personsDAO.findById(id);
		if(person==null) return false;
		person.setDeleted(true);
		try {
			personsDAO.merge(person);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#create()
	 */
	public Persons create() {		
		String id = UUID.randomUUID().toString();
		Persons person = new Persons();
		person.setId(id);
		// default values
		person.setDeleted(false);
		person.setTest(false);
		
		return person;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#get(java.lang.String)
	 */
	public Persons get(String id) {
		Persons person = personsDAO.findById(id);
		if(person==null) return null;
		if(person.getDeleted()) {
			Dictionary.logger.warn("Person marked as deleted");
			return null;
		}
		if(person.getTest()) {
			Dictionary.logger.warn("Person marked for test");
			return null;
		}
		return person;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#save(java.lang.Object)
	 */
	public void save(IEntityObject entityObject) {
		Persons person = (Persons)entityObject;
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(person.getCreatedAt() == null) person.setCreatedAt(now);
		if(person.getCreatedBy() == null) person.setCreatedBy(dictionaryService.getMyUser().getId());
		person.setModifiedAt(now);
		person.setModifiedBy(dictionaryService.getMyUser().getId());
		personsDAO.merge(person);
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#reload(trimatrix.entities.IEntityObject)
	 */
	public void reload(IEntityObject entityObject) {
		Persons person = (Persons)entityObject;
		personsDAO.reload(person);
	}
	
	public static class Data implements IEntityData {
		public String id;
		public String salutationKey;
		public String name_first;
		public String name_last;
		public String sex;
		public String street;
		public String housenumber;
		public String postcode;
		public String city;
		public String state;
		public String countryKey;
		public String email;
		public String homepage;
		public String telephone;
		public String mobile;
		public String fax;
		public Timestamp birthdate;
		
		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			// same as DB entity implementation
			return (name_first + " " + name_last).replace(Constants.NULL, Constants.EMPTY).trim();
		}

		public String getSalutationKey() {
			return salutationKey;
		}

		public String getName_first() {
			return name_first;
		}

		public String getName_last() {
			return name_last;
		}

		public String getSex() {
			return sex;
		}

		public String getStreet() {
			return street;
		}

		public String getHousenumber() {
			return housenumber;
		}

		public String getPostcode() {
			return postcode;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getCountryKey() {
			return countryKey;
		}

		public String getEmail() {
			return email;
		}

		public String getHomepage() {
			return homepage;
		}

		public String getTelephone() {
			return telephone;
		}

		public String getMobile() {
			return mobile;
		}

		public String getFax() {
			return fax;
		}

		public Timestamp getBirthdate() {
			return birthdate;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((birthdate == null) ? 0 : birthdate.hashCode());
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result
					+ ((countryKey == null) ? 0 : countryKey.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((fax == null) ? 0 : fax.hashCode());
			result = prime * result
					+ ((homepage == null) ? 0 : homepage.hashCode());
			result = prime * result
					+ ((housenumber == null) ? 0 : housenumber.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((mobile == null) ? 0 : mobile.hashCode());
			result = prime * result
					+ ((name_first == null) ? 0 : name_first.hashCode());
			result = prime * result
					+ ((name_last == null) ? 0 : name_last.hashCode());
			result = prime * result
					+ ((postcode == null) ? 0 : postcode.hashCode());
			result = prime * result
					+ ((salutationKey == null) ? 0 : salutationKey.hashCode());
			result = prime * result
					+ ((sex == null) ? 0 : sex.hashCode());
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			result = prime * result
					+ ((street == null) ? 0 : street.hashCode());
			result = prime * result
					+ ((telephone == null) ? 0 : telephone.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Data other = (Data) obj;
			if (birthdate == null) {
				if (other.birthdate != null)
					return false;
			} else if (!birthdate.equals(other.birthdate))
				return false;
			if (city == null) {
				if (other.city != null)
					return false;
			} else if (!city.equals(other.city))
				return false;
			if (countryKey == null) {
				if (other.countryKey != null)
					return false;
			} else if (!countryKey.equals(other.countryKey))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (fax == null) {
				if (other.fax != null)
					return false;
			} else if (!fax.equals(other.fax))
				return false;
			if (homepage == null) {
				if (other.homepage != null)
					return false;
			} else if (!homepage.equals(other.homepage))
				return false;
			if (housenumber == null) {
				if (other.housenumber != null)
					return false;
			} else if (!housenumber.equals(other.housenumber))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (mobile == null) {
				if (other.mobile != null)
					return false;
			} else if (!mobile.equals(other.mobile))
				return false;
			if (name_first == null) {
				if (other.name_first != null)
					return false;
			} else if (!name_first.equals(other.name_first))
				return false;
			if (name_last == null) {
				if (other.name_last != null)
					return false;
			} else if (!name_last.equals(other.name_last))
				return false;
			if (postcode == null) {
				if (other.postcode != null)
					return false;
			} else if (!postcode.equals(other.postcode))
				return false;
			if (salutationKey == null) {
				if (other.salutationKey != null)
					return false;
			} else if (!salutationKey.equals(other.salutationKey))
				return false;
			if (sex == null) {
				if (other.sex != null)
					return false;
			} else if (!sex.equals(other.sex))
				return false;
			if (state == null) {
				if (other.state != null)
					return false;
			} else if (!state.equals(other.state))
				return false;
			if (street == null) {
				if (other.street != null)
					return false;
			} else if (!street.equals(other.street))
				return false;
			if (telephone == null) {
				if (other.telephone != null)
					return false;
			} else if (!telephone.equals(other.telephone))
				return false;
			return true;
		}		
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	public void setPersonsDAO(IPersonsDAO personsDAO) {
		this.personsDAO = personsDAO;
	}
}
