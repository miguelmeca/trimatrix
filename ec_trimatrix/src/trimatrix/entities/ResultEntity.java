package trimatrix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Results;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;

public final class ResultEntity extends AEntity {
	
	// Constants	 
    public static final String COMMENT = "comment";
    public static final String COMPETITION = "competition";
    public static final String ATHLETE = "athlete";
    public static final String SCOUT = "scout";
    public static final String FINALPOSITION = "final_position";
    public static final String TIME = "time";
    
	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Wettkampf",COMPETITION, SGridMetaData.Component.FIELD));    
        gridMetaData.add(new SGridMetaData("Scouter", SCOUT, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Athlet", ATHLETE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Kommentar",COMMENT, SGridMetaData.Component.FIELD));      
        gridMetaData.add(new SGridMetaData("Rang",FINALPOSITION, SGridMetaData.Component.FIELD));    
        gridMetaData.add(new SGridMetaData("Zeit",TIME, SGridMetaData.Component.FIELD));    
        return gridMetaData;
    }
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData()
	 */
	public List<IEntityData> getData() {
		return sqlExecutorService.getResultEntities();
	}
		
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity)
	 */
	public List<IEntityData> getData(Constants.Entity entity) {
		if (entity == Constants.Entity.RESULT) {
			return sqlExecutorService.getResultEntities();
        } else if(entity == Constants.Entity.SCOUTRESULTS) {
        	return sqlExecutorService.getResultEntities(null, null, dictionaryService.getMyPerson().getId(), null);
        } else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId) {		
		if (entity == Constants.Entity.MYRESULTS) {
        	return sqlExecutorService.getResultEntities(null, null, dictionaryService.getMyPerson().getId(), personId);
        }  else {
        	return Constants.EMPTYENTITYLIST;
        }		
	}
	
	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getResultEntities(id, null, null, null);
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
					Results entity = (Results)entitiesDAO.findById(id);
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
				logger.info("ResultEntity : Deletion of result successful => " + id );
				return true;
			}			
		});		
		return result;		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#create()
	 */
	public Results create() {		
		String id = UUID.randomUUID().toString();
		Results entity = new Results();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);		
		return entity;
	}	

	public static class Data implements IEntityData {
		public String id;
		public String competition;
		public String scout;
		public String athlete;
		public String final_position;
		public String time;
		public String comment;
		
		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}
		
		@Override
		public String toString() {
			// same as DB entity implementation
			return comment;
		}

		public String getCompetition() {
			return competition;
		}

		public String getScout() {
			return scout;
		}

		public String getAthlete() {
			return athlete;
		}

		public String getFinal_position() {
			return final_position;
		}

		public String getTime() {
			return time;
		}

		public String getComment() {
			return comment;
		}			
	}
}
