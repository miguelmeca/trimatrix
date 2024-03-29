package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.Users;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Role;

public final class PersonEntity extends AEntity {
	public PersonEntity() {
		super(Persons.class);
	}

	// Constants
	public static final String SALUTATION = "salutation";
	public static final String NAME_FIRST = "nameFirst";
    public static final String NAME_LAST = "nameLast";
    public static final String EMAIL = "email";
    public static final String SEX = "sex";
    public static final String BIRTHDATE = "birthdate";
    public static final String STREET = "street";
    public static final String HOUSENUMBER = "housenumber";
    public static final String POSTCODE = "postcode";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";
    public static final String COUNTRYKEY = "countryKey";
    public static final String HOMEPAGE = "homepage";
    public static final String TELEPHONE = "telephone";
    public static final String MOBILE = "mobile";
    public static final String FAX = "fax";

    public static final String HEIGHT = "height";
	public static final String HEIGHT_UNIT = "height_unit";
    public static final String WEIGHT = "weight";
    public static final String WEIGHT_UNIT = "weight_unit";
    public static final String MAX_HR = "max_hr";
    public static final String RESTING_HR = "resting_hr";
    public static final String VO2_MAX = "vo2_max";

	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("salutation"), SALUTATION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("person_first_name"), NAME_FIRST, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("person_last_name"), NAME_LAST, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("email"), EMAIL, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("sex"), SEX, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("birthday"), BIRTHDATE, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("street"), STREET, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("housenumber"), HOUSENUMBER, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("postcode"), POSTCODE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("city"), CITY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("state"), STATE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("country"), COUNTRY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("homepage"), HOMEPAGE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("telephone"), TELEPHONE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("mobile"), MOBILE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("fax"), FAX, SGridMetaData.Component.FIELD));
        return gridMetaData;
    }

//	@Override
//	public Map<String, SSearchMetaData> getSearchMetaData() {
//		Map<String, SSearchMetaData> searchMetaData = new HashMap<String, SSearchMetaData>();
//		searchMetaData.put(NAME_FIRST, new SSearchMetaData(Helper.getLiteral("person_first_name"), NAME_FIRST, SSearchMetaData.Type.STRING, new SRange<String>(NAME_FIRST)));
//		searchMetaData.put(NAME_LAST, new SSearchMetaData(Helper.getLiteral("person_last_name"), NAME_LAST, SSearchMetaData.Type.STRING, new SRange<String>(NAME_FIRST)));
//		searchMetaData.put(COUNTRYKEY, new SSearchMetaData(Helper.getLiteral("country"), COUNTRYKEY, new SRange<String>(COUNTRY), "#{helper.vvb.country}"));
//		return searchMetaData;
//	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData()
	 */
	public List<IEntityData> getData() {
		return sqlExecutorService.getPersonEntities();
	}

	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getPersonEntities(SQLExecutorService.ID, id);
		if (result.size()==0) return null;
		return result.get(0);
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String filter) {
		if (entity == Constants.Entity.PERSON) {
        	return sqlExecutorService.getPersonEntities();
        } else if (entity == Constants.Entity.MYATHLETES) {
        	return sqlExecutorService.getPersonRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.COACH, false);
        } else if (entity == Constants.Entity.MYCOACHES) {
        	return sqlExecutorService.getPersonRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.COACH, true);
        } else if (entity == Constants.Entity.MYSCOUTEDATHLETES) {
        	return sqlExecutorService.getPersonRelationEntities(dictionaryService.getMyPerson().getId(), Constants.Relation.SCOUT, false);
        } else if (entity == Constants.Entity.SCOUTS) {
        	return sqlExecutorService.getPersonByRoleEntities(Role.SCOUTER);
        } else if (entity == Constants.Entity.ATHLETES) {
        	return sqlExecutorService.getAthleteEntities();
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) {
		if (entity == Constants.Entity.PERSON) {
        	return sqlExecutorService.getPersonRelationEntities(personId, Constants.Relation.COACH, true);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#delete(java.lang.String)
	 */
	@Override
	public boolean delete(final String id) {
		// all in one transaction
		final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		Boolean result = (Boolean)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				try {
					Persons entity = (Persons)entitiesDAO.findById(id);
					if(entity==null) return false;
					// check if user admin or creator
					if (dictionaryService.getMyRoles().contains(Constants.Role.ADMIN.getName())||entity.getCreatedBy().equals(dictionaryService.getMyUser().getId())) {
						entity.setDeleted(true);
						entitiesDAO.merge(entity);
						int deleted = daoLayer.deleteRelationsByPartner(id);
						// delete one-to-one relationship to user entity
						List<Users> users = daoLayer.getUsersDAO().findByProperty(UserEntity.PERSON, entity);
						for(Users user : users) {
							user.setPerson(null);
							daoLayer.getUsersDAO().merge(user);
						}
						Statusbar.outputSuccess(String.format(Helper.getMessages("del_entity_success"), deleted));
					} else {
						Statusbar.outputAlert(Helper.getMessages("del_entity_admin")).setLeftTopReferenceCentered();
						return false;
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}
				logger.info("PersonEntity : Deletion of person successful => " + id );
				return true;
			}

		});
		return result;
	}

	public boolean deleteProfile(String id, Constants.Profiles profile) {
		switch (profile) {
		case ATHLETE:
			PersonsAthlete athlete = daoLayer.getPersonAthleteDAO().findById(id);
			if(athlete==null) {
				logger.warn("PersonAthlete : Profil athlete not found => " + id );
				return false;
			}
			daoLayer.getPersonAthleteDAO().delete(athlete);
			logger.info("PersonAthlete : Deletion of profil athlete successful => " + id );
			break;
		}
		return true;
	}

	public boolean createProfile(String id, Constants.Profiles profile) {
		switch (profile) {
		case ATHLETE:
			PersonsAthlete athlete = new PersonsAthlete(id);
			daoLayer.getPersonAthleteDAO().save(athlete);
			logger.info("PersonAthlete : Creation of profil athlete successful => " + id );
			break;
		}
		return true;
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
		// default athletes profile
		entity.setProfileAthlete(new PersonsAthlete(id));
		return entity;
	}

	public static class Data implements IEntityData {
		public String id;
		public String salutation;
		public String nameFirst;
		public String nameLast;
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
		public Boolean standard;

		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			// same as DB entity implementation
			return (nameFirst + " " + nameLast).replace(Constants.NULL, Constants.EMPTY).trim();
		}

		public String getSalutation() {
			return salutation;
		}

		public String getNameFirst() {
			return nameFirst;
		}

		public String getNameLast() {
			return nameLast;
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

		public Boolean getStandard() {
			return standard;
		}
	}
}
