package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import trimatrix.db.Schedules;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants.Entity;

public class ScheduleEntity extends AEntity {

	// Constants
	public static final String PERSON = "person";
	public static final String TYPE = "type";
	public static final String DESCRIPTION = "description";
	public static final String START = "start";
    public static final String DURATION = "druration";
    public static final String COLOR = "color";

	@Override
	public IEntityObject create() {
		String id = UUID.randomUUID().toString();
		Schedules entity = new Schedules();
		entity.setId(id);
		// default values
		entity.setDeleted(false);
		entity.setTest(false);
		return entity;
	}

	@Override
	public List<IEntityData> getData(Entity entity, String personId, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntityData> getData(Entity entity, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntityData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SGridMetaData> getGridMetaData() {
		List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("Person", PERSON, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Typ", TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Beschreibung", DESCRIPTION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Start", START, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData("Dauer", DURATION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("Farbe", COLOR, SGridMetaData.Component.CALENDARFIELD));
        return gridMetaData;
	}

	public static class Data implements IEntityData {
		public String id;
		public String type;
		public String person;
		public String description;
		public Timestamp start;
		public Integer duration;
		public String color;

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

		public Date getStart() {
			return start;
		}

		public Integer getDuration() {
			return duration;
		}

		public String getColor() {
			return color;
		}

		public Date getEnd() {
			return new Date(getStart().getTime() + duration);
		}


	}

}
