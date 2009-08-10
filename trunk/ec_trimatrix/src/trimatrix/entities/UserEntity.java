package trimatrix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Users;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public final class UserEntity extends AEntity {
	// Constants
	public static final String USER_NAME = "user_name";
	public static final String EMAIL = "email";
	public static final String LANGUAGE = "language";
	public static final String CURRENCY = "currency";
	public static final String PERSON = "person";
	public static final String ACTIVE = "active";
	public static final String INITIAL = "initial";
	public static final String LOCKED = "locked";	
	
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
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId) {
		return getData();
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData()
	 */
	public List<IEntityData> getData() {
		return sqlExecutorService.getUserEntities();
	}
	
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#create()
	 */
	public Users create() {		
		String id = UUID.randomUUID().toString();
		Users entity = new Users();
		entity.setId(id);
		// default values
		entity.setActive(false);
		entity.setInitial(false);
		entity.setLocked(false);
		entity.setDeleted(false);
		entity.setTest(false);
		
		return entity;
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
}
