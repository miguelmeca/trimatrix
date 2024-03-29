package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.UserPreferences;
import trimatrix.db.Users;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public final class UserEntity extends AEntity {
	public UserEntity() {
		super(Users.class);
	}

	// Constants
	public static final String USER_NAME = "user_name";
	public static final String EMAIL = "email";
	public static final String LANGUAGE = "language";
	public static final String CURRENCY = "currency";
	public static final String PERSON = "person";
	public static final String ACTIVE = "active";
	public static final String INITIAL = "initial";
	public static final String LOCKED = "locked";
	public static final String LASTLOGON = "last_login";
	public static final String LASTLOGONIP = "last_login_ip";

	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Benutzername", USER_NAME, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("email"), EMAIL, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("language"), LANGUAGE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("currency"), CURRENCY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("person"), PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("locked"), LOCKED, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("initial"), INITIAL, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("active"), ACTIVE, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("last_logon"), LASTLOGON, SGridMetaData.Component.FORMATED_DATETIME));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("last_logon_ip"), LASTLOGONIP, SGridMetaData.Component.FIELD));
        return gridMetaData;
    }

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String filter) {
		return getData();
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) {
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
		// preferences
		UserPreferences userPreferences = new UserPreferences(id);
		// if no default value set, than no rows are shown!
		userPreferences.setSbvisibleamount(25);
		entity.setPreferences(userPreferences);
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
		public Timestamp last_login;
		public String last_login_ip;

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

		public Timestamp getLast_login() {
			return last_login;
		}

		public String getLast_login_ip() {
			return last_login_ip;
		}
	}
}
