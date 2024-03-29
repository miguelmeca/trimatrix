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

import trimatrix.db.Tests;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public final class TestEntity extends AEntity {
	public TestEntity() {
		super(Tests.class);
	}

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
    public static final String ANALYZED = "analyzed";

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

    // Constants Protocol
    public static final String PROTOCOL_DESCRIPTION = "protocol_description";
    public static final String MODEL = "model";
    public static final String MODEL_LACTATE = "model_lactate";
    public static final String MODEL_SPIRO = "model_spiro";
    public static final String PERFORMANCE_MAX = "performance_max";

    // Constants Analysis
    public static final String ANALYSIS_FUNCTION = "analysis_function";
    public static final String ANALYSIS_OFFSET = "analysis_offset";
    public static final String ANALYSIS_DEGREE = "analysis_degree";
    public static final String ANALYSIS_LACTATE_HR = "analysis_lactate_hr";

	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("test_date"), DATE, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("type"), TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("person"), PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("doctor"), DOCTOR, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("coach"), COACH, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("description"), DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("protocol"), PROTOCOL, SGridMetaData.Component.CHECKBOX));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("analyzed"), ANALYZED, SGridMetaData.Component.CHECKBOX));
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
		if (entity == Constants.Entity.TEST) {
			return sqlExecutorService.getTestEntities();
        } else if (entity == Constants.Entity.MYTESTS) {
        	return sqlExecutorService.getTestEntities(PERSON, dictionaryService.getMyPerson().getId());
        } else if (entity == Constants.Entity.COACHTESTS) {
        	return sqlExecutorService.getTestEntities(COACH, dictionaryService.getMyPerson().getId());
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) {
		if (entity == Constants.Entity.TEST) {
			return sqlExecutorService.getTestEntities(PERSON, personId);
		} else if (entity == Constants.Entity.COACHTESTS) {
			return sqlExecutorService.getTestEntities(COACH, personId);
		} else {
        	return Constants.EMPTYENTITYDATA;
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
						Statusbar.outputSuccess(String.format(Helper.getMessages("del_entity_success"), deleted));
					} else {
						Statusbar.outputAlert(Helper.getMessages("del_entity_admin")).setLeftTopReferenceCentered();
						return false;
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
					return false;
				}
				logger.info("TestEntity : Deletion of test successful => " + id );
				return true;
			}
		});
		return result;
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
			Statusbar.outputAlert(ex.toString(), Helper.getMessages("copy_failure")).setLeftTopReferenceCentered();
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
		public boolean analyzed;
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

		public boolean getAnalyzed() {
			return analyzed;
		}

		public Timestamp getDate() {
			return date;
		}
	}
}
