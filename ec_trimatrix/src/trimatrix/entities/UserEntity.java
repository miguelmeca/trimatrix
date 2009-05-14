package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.IUsersDAO;
import trimatrix.db.Users;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public final class UserEntity implements IEntity {
	// Constants
	public static final String USER_NAME = "user_name";
	public static final String EMAIL = "email";
	public static final String LANGUAGE = "language";
	public static final String CURRENCY = "currency";
	public static final String PERSON = "person";
	public static final String ACTIVE = "active";
	public static final String INITIAL = "initial";
	public static final String LOCKED = "locked";
	
	// Variables
	private SQLExecutorService sqlExecutorService;	
	private Dictionary dictionaryService;
	private IUsersDAO usersDAO;
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();        
        gridMetaData.add(new SGridMetaData("Benutzername", USER_NAME, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Email", EMAIL, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Sprache", LANGUAGE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Währung", CURRENCY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("gesperrt", LOCKED, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData("initial", INITIAL, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData("aktiv", ACTIVE, SGridMetaData.Component.CHECKBOX));
        return gridMetaData;
    }	
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity)
	 */
	public List<IEntityData> getData(Constants.Entity entity) {
		return getData();
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData()
	 */
	public List<IEntityData> getData() {
		return sqlExecutorService.getUserEntities();
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#delete(java.lang.String)
	 */
	public boolean delete(String id) {
		Users user = usersDAO.findById(id);
		if(user==null) return false;
		user.setDeleted(true);
		try {
			usersDAO.merge(user);
		} catch (Exception ex) {
			return false;
		}
		return true;		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#get(java.lang.String)
	 */
	public Users get(String id) {
		Users user = usersDAO.findById(id);
		if(user==null) return null;
		if(user.getDeleted()) {
			Dictionary.logger.warn("User marked as deleted");
			return null;
		}
		if(user.getTest()) {
			Dictionary.logger.warn("User marked for test");
			return null;
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#create()
	 */
	public Users create() {		
		String id = UUID.randomUUID().toString();
		Users user = new Users();
		user.setId(id);
		// default values
		user.setActive(false);
		user.setInitial(false);
		user.setLocked(false);
		user.setDeleted(false);
		user.setTest(false);
		
		return user;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#save(java.lang.Object)
	 */
	public void save(IEntityObject entityObject) {
		Users user = (Users)entityObject;
		// set creation data
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		if(user.getCreatedAt() == null) user.setCreatedAt(now);
		if(user.getCreatedBy() == null) user.setCreatedBy(dictionaryService.getMyUser().getId());
		user.setModifiedAt(now);
		user.setModifiedBy(dictionaryService.getMyUser().getId());
		usersDAO.merge(user);
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#reload(trimatrix.entities.IEntityObject)
	 */
	public void reload(IEntityObject entityObject) {
		Users user = (Users)entityObject;
		usersDAO.reload(user);
	}
	
	public static class Data implements IEntityData {
		public String id;
		public String user_name;
		public String language;
		public String currency;
		public String email;
		public String person;
		public boolean locked;
		public boolean initial;
		public boolean active;		
		
		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			// same as DB entity implementation
			return user_name;
		}

		public String getUser_name() {
			return user_name;
		}
		
		public String getPerson() {
			return person;
		}

		public String getLanguage() {
			return language;
		}

		public String getCurrency() {
			return currency;
		}
		
		public String getEmail() {
			return email;
		}

		public boolean getLocked() {
			return locked;
		}

		public boolean getInitial() {
			return initial;
		}

		public boolean getActive() {
			return active;
		}		
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}	

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	
}
