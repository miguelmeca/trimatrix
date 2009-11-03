package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Competitions;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public final class CompetitionEntity extends AEntity {
	
	// Constants	 
	public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";
    public static final String COUNTRY = "country";
    public static final String SWIMSUIT = "swimsuit";
    
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Datum", DATE, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData("Beschreibung", DESCRIPTION, SGridMetaData.Component.FIELD));        
        gridMetaData.add(new SGridMetaData("Typ", TYPE, SGridMetaData.Component.FIELD));        
        gridMetaData.add(new SGridMetaData("Adresse", ADDRESS, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Land", COUNTRY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Neopren", SWIMSUIT, SGridMetaData.Component.FIELD));      
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
		if (entity == Constants.Entity.COMPETITION) {
			//TODO return sqlExecutorService.getCompetitionEntities();
			return null;
        } else if (entity == Constants.Entity.MYCOMPETITIONS) {
        	// TODO return sqlExecutorService.getTestEntities(PERSON, dictionaryService.getMyPerson().getId());
        	return null;
        } else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId) {		
		if (entity == Constants.Entity.COMPETITION) {
			// TODO return sqlExecutorService.getCompetitionEntities(PERSON, personId);
			return null;
		} else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	@Override
	public IEntityData getData(String id) {
		// TODO List<IEntityData> result = sqlExecutorService.getCompetitionEntities(SQLExecutorService.ID, id);
		//if (result.size()==0) return null;
		//return result.get(0);
		return null;
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
					Competitions entity = (Competitions)entitiesDAO.findById(id);
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
				logger.info("CompetitonEntity : Deletion of doctor successful => " + id );
				return true;
			}			
		});		
		return result;		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#create()
	 */
	public Competitions create() {		
		String id = UUID.randomUUID().toString();
		Competitions entity = new Competitions();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}	

	public static class Data implements IEntityData {
		public String id;
		public Timestamp date;
		public String description;
		public String address;
		public String country;
		public boolean swimsuit;		
		
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

		public Timestamp getDate() {
			return date;
		}

		public String getDescription() {
			return description;
		}

		public String getAddress() {
			return address;
		}

		public String getCountry() {
			return country;
		}

		public boolean getSwimsuit() {
			return swimsuit;
		}		
	}
}
