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
    public static final String STREET = "street";
    public static final String HOUSENUMBER = "housenumber";  
    public static final String POSTCODE = "postcode"; 
    public static final String CITY = "city"; 
    public static final String STATE = "state"; 
    public static final String COUNTRY = "country"; 
    public static final String HOMEPAGE = "homepage";
    public static final String TELEPHONE = "telephone";
    public static final String MOBILE = "mobile";
    public static final String FAX = "fax";
	
    // Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IPersonsDAO entitiesDAO;
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
        gridMetaData.add(new SGridMetaData("Straße", STREET, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Hausnummer", HOUSENUMBER, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Postleitzahl", POSTCODE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Stadt", CITY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Bundesland", STATE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Land", COUNTRY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Homepage", HOMEPAGE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Telefon", TELEPHONE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Handy", MOBILE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Fax", FAX, SGridMetaData.Component.FIELD));
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
					Persons entity = entitiesDAO.findById(id);
					if(entity==null) return false;
					entity.setDeleted(true);
					entitiesDAO.merge(entity);					
					// delete relationships
					List<Users> users = usersDAO.findByProperty(UserEntity.PERSON, entity);
					for(Users user : users) {
						user.setPerson(null);
						usersDAO.merge(user);
					}				
					// TODO delete PersonPersonRelations
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
		Persons entity = new Persons();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#get(java.lang.String)
	 */
	public Persons get(String id) {
		Persons entity = entitiesDAO.findById(id);
		if(entity==null) return null;
		if(entity.getDeleted()) {
			Dictionary.logger.warn("Person marked as deleted");
			return null;
		}
		if(entity.getTest()) {
			Dictionary.logger.warn("Person marked for test");
			return null;
		}
		return entity;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#save(java.lang.Object)
	 */
	public void save(IEntityObject entityObject) {
		Persons entity = (Persons)entityObject;
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(entity.getCreatedAt() == null) entity.setCreatedAt(now);
		if(entity.getCreatedBy() == null) entity.setCreatedBy(dictionaryService.getMyUser().getId());
		entity.setModifiedAt(now);
		entity.setModifiedBy(dictionaryService.getMyUser().getId());
		entitiesDAO.merge(entity);
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#reload(trimatrix.entities.IEntityObject)
	 */
	public void reload(IEntityObject entityObject) {
		Persons entity = (Persons)entityObject;
		entitiesDAO.reload(entity);
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
		public String country;
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

		public String getCountry() {
			return country;
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

	public void setEntitiesDAO(IPersonsDAO entitiesDAO) {
		this.entitiesDAO = entitiesDAO;
	}

	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}	
}
