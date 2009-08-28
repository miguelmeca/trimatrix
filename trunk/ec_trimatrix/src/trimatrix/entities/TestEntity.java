package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.PersonsAthlete;
import trimatrix.db.Tests;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public final class TestEntity extends AEntity {
	// Enums
	public static enum TYPE {
		ERGO, TREADMILL
	}
	
	// Constants	 
	public static final String TYPE = "type";
    public static final String PERSON = "person";  
    public static final String DOCTOR = "doctor";
    public static final String COACH = "coach";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String PROTOCOL = "protocol";
    
    // Constants Ergo
    public static final String POWER_INIT = "power_init";
    public static final String POWER_STEP = "power_step";  
    public static final String CADENCE_LOW = "cadence_low";
    public static final String CADENCE_HIGH = "cadence_high";
    public static final String ERGO_STEP_TIME = "ergo_step_time";    
 	
    // Constants Treadmill
    public static final String SPEED_INIT = "speed_init";
    public static final String SPEED_STEP = "speed_step";  
    public static final String SPEED_VARIABLE = "speed_variable";
    public static final String INCLINE_INIT = "incline_init";
    public static final String INCLINE_STEP = "incline_step";  
    public static final String INCLINE_VARIABLE = "incline_variable";
    public static final String TREADMILL_STEP_TIME = "treadmill_step_time";  
    
    // Constants Swim
    public static final String ASSISTANT_NAME = "assistant_name";
    public static final String BATHS = "baths";  
    public static final String POOL = "pool";
    public static final String DATE2 = "date2";
    public static final String DISTANCE = "distance";  
    public static final String SPLITS = "splits";
    
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Testdatum", DATE, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData("Typ", TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Arzt", DOCTOR, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Trainer", COACH, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beschreibung", DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.protocol}", PROTOCOL, SGridMetaData.Component.CHECKBOX));       
        return gridMetaData;
    }
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData()
	 */
	public List<IEntityData> getData() {
		return sqlExecutorService.getTestEntities();
	}
		
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity)
	 */
	public List<IEntityData> getData(Constants.Entity entity) {
		if (entity == Constants.Entity.TEST) {
			return sqlExecutorService.getTestEntities();
        } else if (entity == Constants.Entity.MYTESTS) {
        	return sqlExecutorService.getTestEntities(PERSON, dictionaryService.getMyPerson().getId());
        } else if (entity == Constants.Entity.COACHTESTS) {
        	return sqlExecutorService.getTestEntities(COACH, dictionaryService.getMyPerson().getId());	
        } else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId) {		
		if (entity == Constants.Entity.TEST) {
			return sqlExecutorService.getTestEntities(PERSON, personId);
		} else if (entity == Constants.Entity.COACHTESTS) {
			return sqlExecutorService.getTestEntities(COACH, personId);
		} else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getTestEntities(SQLExecutorService.ID, id);
		if (result.size()==0) return null;
		return result.get(0);
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
					Tests entity = (Tests)entitiesDAO.findById(id);
					if(entity==null) return false;
					// check if user admin or creator
					if (dictionaryService.getMyRoles().contains(Constants.Role.ADMIN.getName())||entity.getCreatedBy().equals(dictionaryService.getMyUser().getId())) {
						entity.setDeleted(true);
						entitiesDAO.merge(entity);
						int deleted = daoLayer.deleteRelationsByPartner(id);
						Statusbar.outputSuccess("Successfully deleted entity incl. " + deleted + " relations!");
					} else {
						Statusbar.outputAlert("Do delete this object you have to be admin or the creator of this object!");
						return false;
					}					
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
	
	public boolean deleteProfile(String id, Constants.Profiles profile) {
		switch (profile) {
		case ATHLETE:
			PersonsAthlete athlete = daoLayer.getPersonAthleteDAO().findById(id);
			if(athlete==null) {
				Dictionary.logger.warn("PersonAthlete : Profil athlete not found => " + id );		
				return false;
			} 
			daoLayer.getPersonAthleteDAO().delete(athlete);		
			Dictionary.logger.info("PersonAthlete : Deletion of profil athlete successful => " + id );
			break;
		}		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#create()
	 */
	public Tests create() {		
		String id = UUID.randomUUID().toString();
		Tests entity = new Tests();
		entity.setId(id);
		// set actual user as coach, normally just coaches are able to create tests
	    entity.setCoach(dictionaryService.getMyPerson());
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}	
	
	
	@Override
	public IEntityObject copy(IEntityObject entityObject) {
		Tests entity = (Tests)entityObject;
		String id = UUID.randomUUID().toString();
		// copy entity
		try {
			Tests entityCopy = (Tests)BeanUtils.cloneBean(entity);
			entityCopy.setId(id);		
			// set actual logged person as coach
			entityCopy.setCoach(dictionaryService.getMyPerson());
			// copy specific data		
			if(entityCopy.getTestsErgo()!=null) entityCopy.getTestsErgo().setId(id);
			if(entityCopy.getTestsTreadmill()!=null) entityCopy.getTestsTreadmill().setId(id);
			if(entityCopy.getTestsSwim()!=null) entityCopy.getTestsSwim().setId(id);
			return entityCopy;
		} catch (Exception ex) {
			Statusbar.outputAlert(ex.toString(), "Copy failed!");
			return null;
		}				
	}
	
	

	@Override
	public boolean isCopyable(IEntityObject entityObject) {
		// just enabled for admins and coaches
		if(dictionaryService.getMyRoles().contains(Constants.Role.ADMIN.getName()) ||
		   dictionaryService.getMyRoles().contains(Constants.Role.COACH.getName())) return true;
		   return false;
	}

	public static class Data implements IEntityData {
		public String id;
		public String type;
		public String person;
		public String doctor;
		public String coach;
		public String description;
		public boolean protocol;
		public Timestamp date;
		
		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			// same as DB entity implementation
			return description;
		}

		public String getType() {
			return type;
		}

		public String getPerson() {
			return person;
		}

		public String getDoctor() {
			return doctor;
		}
		
		public String getCoach() {
			return coach;
		}

		public String getDescription() {
			return description;
		}

		public boolean getProtocol() {
			return protocol;
		}

		public Timestamp getDate() {
			return date;
		}				
	}
}
