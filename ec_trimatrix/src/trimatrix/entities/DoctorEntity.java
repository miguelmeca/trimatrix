package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Doctors;
import trimatrix.db.IDoctorsDAO;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Constants.Entity;

public class DoctorEntity implements IEntity {
	// Constants	 
	public static final String NAME = "name";
	public static final String STREET = "street";
    public static final String HOUSENUMBER = "housenumber";  
    public static final String POSTCODE = "postcode"; 
    public static final String CITY = "city"; 
    public static final String STATE = "state"; 
    public static final String COUNTRY = "country"; 
    public static final String EMAIL = "email";
    public static final String HOMEPAGE = "homepage";
    public static final String TELEPHONE = "telephone";
    public static final String MOBILE = "mobile";
    public static final String FAX = "fax";
    
	// Variables
	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private IDoctorsDAO entitiesDAO;
	private HibernateTransactionManager transactionManager;

	public IEntityObject create() {
		String id = UUID.randomUUID().toString();
		Doctors entity = new Doctors();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}

	public boolean delete(final String id) {
		// all in one transaction		
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Boolean result = (Boolean)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					Doctors entity = entitiesDAO.findById(id);
					if(entity==null) return false;
					entity.setDeleted(true);
					entitiesDAO.merge(entity);
					// TODO delete PersonDoctor relation
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}				
				Dictionary.logger.info("DoctorEntity : Deletion of doctor successful => " + id );
				return true;
			}			
		});		
		return result;		
	}

	public IEntityObject get(String id) {
		Doctors entity = entitiesDAO.findById(id);
		if(entity==null) return null;
		if(entity.getDeleted()) {
			Dictionary.logger.warn("Doctor marked as deleted");
			return null;
		}
		if(entity.getTest()) {
			Dictionary.logger.warn("Doctor marked for test");
			return null;
		}
		return entity;
	}

	public List<IEntityData> getData(Entity entity, String personId) {
		if (entity == Constants.Entity.DOCTOR) {
        	return sqlExecutorService.getDoctorRelationEntities(personId, Constants.Relation.DOCTOR);
        } else {
        	return Constants.EMPTYENTITYLIST;
        }
	}
	
	public List<IEntityData> getData(Entity entity) {
		if (entity == Constants.Entity.DOCTOR) {
        	return sqlExecutorService.getDoctorEntities();
        } else if (entity == Constants.Entity.MYDOCTORS) {
        	return sqlExecutorService.getDoctorRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.DOCTOR);
        } else {
        	return Constants.EMPTYENTITYLIST;
        }
	}

	public List<IEntityData> getData() {
		return sqlExecutorService.getDoctorEntities();
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Name", NAME, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Straße", STREET, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Hausnummer", HOUSENUMBER, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Postleitzahl", POSTCODE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Stadt", CITY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Bundesland", STATE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Land", COUNTRY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Email", EMAIL, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Homepage", HOMEPAGE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Telefon", TELEPHONE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Handy", MOBILE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Fax", FAX, SGridMetaData.Component.FIELD));
        return gridMetaData;
	}

	public void reload(IEntityObject entityObject) {
		Doctors entity = (Doctors)entityObject;
		entitiesDAO.reload(entity);		
	}

	public void save(IEntityObject entityObject) {
		Doctors entity = (Doctors)entityObject;
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(entity.getCreatedAt() == null) entity.setCreatedAt(now);
		if(entity.getCreatedBy() == null) entity.setCreatedBy(dictionaryService.getMyUser().getId());
		entity.setModifiedAt(now);
		entity.setModifiedBy(dictionaryService.getMyUser().getId());
		entitiesDAO.merge(entity);		
	}
	
	public static class Data implements IEntityData {
		public String id;
		public String name;
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
		
		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			// same as DB entity implementation
			return name.replace(Constants.NULL, Constants.EMPTY).trim();
		}
		
		public String getName() {
			return name;
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
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setEntitiesDAO(IDoctorsDAO entitiesDAO) {
		this.entitiesDAO = entitiesDAO;
	}

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
