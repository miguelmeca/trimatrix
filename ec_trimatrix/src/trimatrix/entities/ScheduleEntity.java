package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Schedules;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

public class ScheduleEntity extends AEntity {
	public ScheduleEntity() {
		super(Schedules.class);
	}

	// Constants
	public static final String PERSON = "person";
	public static final String TYPE = "type";
	public static final String DESCRIPTION = "description";
	public static final String START = "start";
    public static final String DURATION = "duration";
    public static final String COLOR = "color";
    public static final String DONE = "done";

	public IEntityObject create() {
		String id = UUID.randomUUID().toString();
		Schedules entity = new Schedules();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);
		entity.setTemplate(false);
		return entity;
	}

	public List<IEntityData> getData(Entity entity, String personId, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IEntityData> getData(Entity entity, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IEntityData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getScheduleEntities(id);
		if (result.size()==0) return null;
		return result.get(0);
	}

	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("person"), PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("type"), TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("description"), DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("start"), START, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("duration"), DURATION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("color"), COLOR, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData(Helper.getLiteral("done"), DONE, SGridMetaData.Component.CHECKBOX));
        return gridMetaData;
	}

	public static class Data implements IEntityData {
		public String id;
		public String type;
		public String person;
		public String description;
		public Timestamp start;
		public Long duration;
		public String color;
		public Boolean template;
		public Boolean done;

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

		public String getDescription() {
			return description;
		}

		public Timestamp getStart() {
			return start;
		}

		public Long getDuration() {
			return duration;
		}

		public String getColor() {
			return color;
		}

		public Timestamp getEnd() {
			return new Timestamp(getStart().getTime() + duration * 60000);
		}

		public Boolean getTemplate() {
			return template;
		}

		public Boolean getDone() {
			return done;
		}


	}

}
