package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.IPersonsDAO;
import trimatrix.db.IUsersDAO;
import trimatrix.db.Persons;
import trimatrix.db.Users;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public final class PersonEntity implements IEntity {
	// Constants	 
	public static final String SALUTATION = "salutation";
	public static final String NAME_FIRST = "name_first";
    public static final String NAME_LAST = "name_last";  
    public static final String EMAIL = "email";
    public static final String SEX = "sex";
    public static final String BIRTHDATE = "birthdate";
	
    // Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IPersonsDAO personsDAO;
	private IUsersDAO usersDAO;
	private HibernateTransactionManager transactionManager;
		
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Anrede", SALUTATION, SGridMetaData.Component.FIELD));
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
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity)
	 */
	public List<IEntityData> getData(Constants.Entity entity) {
		// TODO implement separate logics
		if (entity == Constants.Entity.PERSON) {
        	return sqlExecutorService.getPersonEntities();
        } else if (entity == Constants.Entity.MYATHLETES) {
        	return sqlExecutorService.getPersonRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.COACH, false);
        } else if (entity == Constants.Entity.MYCOACHES) {
        	return sqlExecutorService.getPersonRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.COACH, true);
        } else {
        	return Constants.EMPTYENTITYLIST;
        }
		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#delete(java.lang.String)
	 */
	public boolean delete(final String id) {
		// all in one transaction		
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Boolean result = (Boolean)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					Persons person = personsDAO.findById(id);
					if(person==null) return false;
					person.setDeleted(true);
						personsDAO.merge(person);					
					// delete relationships
					List<Users> users = usersDAO.findByProperty(UserEntity.PERSON, person);
					for(Users user : users) {
						user.setPerson(null);
						usersDAO.merge(user);
					}					
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}				
				Dictionary.logger.info("PersonEntity : Deletion of person successful => " + id );
				return true;
			}
			
		});		
		return result;
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
		public String salutation;
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
		
		public String getSalutation() {
			return salutation;
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
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}	
	
	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setPersonsDAO(IPersonsDAO personsDAO) {
		this.personsDAO = personsDAO;
	}

	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}	
}
