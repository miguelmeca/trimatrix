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
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Relation;

public final class CompetitionEntity extends AEntity {

	// Constants
	public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "type";
    public static final String SUBTYPE = "subtype";
    public static final String ADDRESS = "address";
    public static final String COUNTRY = "country";
    public static final String RESULTS = "results";

    // Comp Types
    public static final String TRIATHLON = "tria";

	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("#{rr.literals.date}", DATE, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.description}", DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.type}", TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.subtype}", SUBTYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.address}", ADDRESS, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.country}", COUNTRY, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.results}", RESULTS, SGridMetaData.Component.CHECKBOX));
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
	public List<IEntityData> getData(Constants.Entity entity, String filter) {
		if (entity == Constants.Entity.COMPETITION) {
			return sqlExecutorService.getCompetitionEntities();
        } else if (entity == Constants.Entity.MYCOMPETITIONS) {
        	return sqlExecutorService.getCompetitionRelationEntities(dictionaryService.getMyPerson().getId(), Relation.COMPETITION);
        } else if (entity == Constants.Entity.SCOUTCOMPETITIONS) {
        	return sqlExecutorService.getCompetitionRelationEntities(dictionaryService.getMyPerson().getId(), Relation.COMPETITIONSCOUT);
        }else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) {
		return null;
	}

	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getCompetitionEntities(SQLExecutorService.ID, id);
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
					Competitions entity = (Competitions)entitiesDAO.findById(id);
					if(entity==null) return false;
					// check if user admin or creator
					if (dictionaryService.getMyRoles().contains(Constants.Role.ADMIN.getName())||entity.getCreatedBy().equals(dictionaryService.getMyUser().getId())) {
						entity.setDeleted(true);
						entitiesDAO.merge(entity);
						int deleted = daoLayer.deleteRelationsByPartner(id);
						Statusbar.outputSuccess("Successfully deleted entity incl. " + deleted + " relations!");
					} else {
						Statusbar.outputAlert("Do delete this object you have to be admin or the creator of this object!").setLeftTopReferenceCentered();
						return false;
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}
				logger.info("CompetitonEntity : Deletion of competition successful => " + id );
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
		public String type;
		public String subtype;
		public String address;
		public String country;
		public boolean results;

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

		public String getType() {
			return type;
		}

		public String getSubtype() {
			return subtype;
		}

		public String getAddress() {
			return address;
		}

		public String getCountry() {
			return country;
		}

		public boolean getResults() {
			return results;
		}
	}
}
