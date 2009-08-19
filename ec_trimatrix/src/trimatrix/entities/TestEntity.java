package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.PersonsAthlete;
import trimatrix.db.Tests;
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
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String PROTOCOL = "protocol";
 	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Typ", TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Arzt", DOCTOR, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beschreibung", DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.protocol}", PROTOCOL, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData("Testdatum", DATE, SGridMetaData.Component.CALENDARFIELD));
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
        	return sqlExecutorService.getTestEntities(dictionaryService.getMyPerson().getId(), null);
        } else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId) {
		if (entity == Constants.Entity.TEST) {
			return sqlExecutorService.getTestEntities(personId, null);
        } else {
        	return Constants.EMPTYENTITYLIST;
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
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}	
	
	public static class Data implements IEntityData {
		public String id;
		public String type;
		public String person;
		public String doctor;
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
