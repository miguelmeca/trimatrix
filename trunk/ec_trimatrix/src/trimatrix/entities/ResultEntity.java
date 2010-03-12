package trimatrix.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Results;
import trimatrix.reports.Report;
import trimatrix.reports.excel.PerformanceChart;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

public final class ResultEntity extends AEntity {

	// Constants
    public static final String COMMENT = "comment";
    public static final String DATE = "date";
    public static final String COMPETITION = "competition";
    public static final String TYPE = "type";
    public static final String ATHLETE = "athlete";
    public static final String SCOUT = "scout";
    public static final String FINALPOSITION = "final_position";
    public static final String TIME = "time";

    // Constants Triathlon
    public static final String SUBTYPE = "subtype";
    public static final String CATEGORY_TRIA = "category_tria";
    public static final String SWIM_SPLIT = "swim_split";
    public static final String RUN_SPLIT = "run_split";
    public static final String BIKE_SPLIT = "bike_split";
    public static final String SWIM_POSITION = "swim_pos";
    public static final String RUN_POSITION = "run_pos";
    public static final String BIKE_POSITION = "bike_pos";
    public static final String BEST_SWIM_SPLIT = "best_swim_split";
    public static final String BEST_RUN_SPLIT = "best_run_split";
    public static final String BEST_BIKE_SPLIT = "best_bike_split";
    public static final String BEST_SWIMMER = "best_swimmer";
    public static final String BEST_BIKER = "best_biker";
    public static final String BEST_RUNNER = "best_runner";
    public static final String SWIM_DEFICIT = "swim_def";
    public static final String RUN_DEFICIT = "run_def";
    public static final String BIKE_DEFICIT = "bike_def";
    public static final String SWIM_DEFICIT_PER = "swim_def_per";
    public static final String RUN_DEFICIT_PER = "run_def_per";
    public static final String BIKE_DEFICIT_PER = "bike_def_per";
    public static final String SWIM_COLOR = "swim_color";
    public static final String RUN_COLOR = "run_color";
    public static final String BIKE_COLOR = "bike_color";
    public static final String SWIMSUIT = "swimsuit";
    public static final String SWIM_CUTOFF = "swim_cutoff";
    public static final String RUN_CUTOFF = "run_cutoff";
    public static final String BIKE_CUTOFF = "bike_cutoff";

    // Constants for SGRIDMETADATA
    public static final String META_SWIM = "<t:field text='.{datum." + SWIM_SPLIT + "}' background='.{datum." + SWIM_COLOR + "}' enabled='false' align='center'/>";
    public static final String META_RUN = "<t:field text='.{datum." + RUN_SPLIT + "}' background='.{datum." + RUN_COLOR + "}' enabled='false' align='center'/>";
    public static final String META_BIKE = "<t:field text='.{datum." + BIKE_SPLIT + "}' background='.{datum." + BIKE_COLOR + "}' enabled='false' align='center'/>";

	/* (non-Javadoc)
	 * @see trimatrix.entities.IUserEntity#getGridMetaData()
	 */
	public List<SGridMetaData> getGridMetaData() {
        List<SGridMetaData> gridMetaData = new ArrayList<SGridMetaData>();
        gridMetaData.add(new SGridMetaData("#{rr.literals.date}", DATE, SGridMetaData.Component.CALENDARFIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.competition}",COMPETITION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.type}", TYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.subtype}", SUBTYPE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.scouter}", SCOUT, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.athlete}", ATHLETE, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.comment}",COMMENT, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.ranking}",FINALPOSITION, SGridMetaData.Component.FIELD));
        gridMetaData.add(new SGridMetaData("#{rr.literals.time}",TIME, SGridMetaData.Component.FIELD));
        return gridMetaData;
    }

	public List<SGridMetaData> getGridMetaData(String filter) {
		// get general Meta Data
		List<SGridMetaData> gridMetaData = getGridMetaData();
		// add specific data
		if(CompetitionEntity.TRIATHLON.equalsIgnoreCase(filter)) {
			gridMetaData.add(new SGridMetaData("#{rr.literals.subtype}", SUBTYPE, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.category}",CATEGORY_TRIA, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.swimsuit}",SWIMSUIT, SGridMetaData.Component.CHECKBOX));

			gridMetaData.add(new SGridMetaData("#{rr.literals.best_swim_split}",BEST_SWIM_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.best_swimmer}",BEST_SWIMMER, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.cutoff_swim}",SWIM_CUTOFF, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.swim_split}",SWIM_COLOR, META_SWIM));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.swim_split}",SWIM_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.ranking_swim}",SWIM_POSITION, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_swim}",SWIM_DEFICIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_percent}",SWIM_DEFICIT_PER, SGridMetaData.Component.FORMATED_DOUBLE));

			gridMetaData.add(new SGridMetaData("#{rr.literals.best_run_split}",BEST_RUN_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.best_runner}",BEST_RUNNER, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.cutoff_run}",RUN_CUTOFF, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.run_split}",RUN_COLOR, META_RUN));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.run_split}",RUN_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.ranking_run}",RUN_POSITION, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_run}",RUN_DEFICIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_percent}",RUN_DEFICIT_PER, SGridMetaData.Component.FORMATED_DOUBLE));

			gridMetaData.add(new SGridMetaData("#{rr.literals.best_bike_split}",BEST_BIKE_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.best_biker}",BEST_BIKER, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.cutoff_bike}",BIKE_CUTOFF, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.bike_split}",BIKE_COLOR, META_BIKE));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.bike_split}",BIKE_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.ranking_bike}",BIKE_POSITION, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_bike}",BIKE_DEFICIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_percent}",BIKE_DEFICIT_PER, SGridMetaData.Component.FORMATED_DOUBLE));

		}
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
	public List<IEntityData> getData(Constants.Entity entity, String filter) {
		if (entity == Constants.Entity.RESULT) {
			return sqlExecutorService.getResultEntities();
        } else if(entity == Constants.Entity.SCOUTRESULTS) {
        	String type = null;
			String subtype = null;
			// Check if subtype is submitted and relevant
			if(filter!=null) {
				String[] filters = filter.split(":");
				if(filters.length==2) {
					subtype = filters[1];
				}
				type = filters[0];
			}
        	return sqlExecutorService.getResultEntities(null, null, dictionaryService.getMyPerson().getId(), null, type, subtype);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) {
		if (entity == Constants.Entity.MYRESULTS) {
			String type = null;
			String subtype = null;
			// Check if subtype is submitted and relevant
			if(filter!=null) {
				String[] filters = filter.split(":");
				if(filters.length==2) {
					subtype = filters[1];
				}
				type = filters[0];
			}
        	return sqlExecutorService.getResultEntities(null, null, dictionaryService.getMyPerson().getId(), personId, type, subtype);
        }  else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getResultEntities(id, null, null, null, null, null);
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
						Statusbar.outputAlert("Do delete this object you have to be admin or the creator of this object!").setLeftTopReferenceCentered();
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

	@Override
	public Report getPrintReport(Entity entity, String filter, List<IEntityData> data) {
		if(Helper.isEmpty(filter)) return null;
		if (entity == Constants.Entity.MYRESULTS && filter.startsWith(CompetitionEntity.TRIATHLON)) {
			PerformanceChart.Data reportData = new PerformanceChart.Data();
			for(IEntityData entityData : data) {
				ResultEntity.Data resultData = (ResultEntity.Data)entityData;
				if(reportData.name==null) reportData.name = resultData.athlete;
				reportData.items.add(resultData);
			}
			return new PerformanceChart(reportData);
		}
		return null;
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
		public Timestamp date;
		public String competition;
		public String type;
		public String subtype;
		public String scout;
		public String athlete;
		public String final_position;
		public String time;
		public String comment;
		public String category_tria;
		public Boolean swimsuit;
		public String swim_split;
		public String swim_pos;
		public String best_swim_split;
		public String run_split;
		public String run_pos;
		public String best_run_split;
		public String bike_split;
		public String bike_pos;
		public String best_bike_split;
		public String swim_color;
		public String run_color;
		public String bike_color;
		public String swim_cutoff;
		public String run_cutoff;
		public String bike_cutoff;
		public String best_swimmer;
		public String best_runner;
		public String best_biker;

		/* (non-Javadoc)
		 * @see trimatrix.entities.IEntityData#getId()
		 */
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			// same as DB entity implementation
			return competition;
		}

		public String getCompetition() {
			return competition;
		}

		public String getType() {
			return type;
		}

		public String getSubtype() {
			return subtype;
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

		public String getCategory_tria() {
			return category_tria;
		}

		public Boolean getSwimsuit() {
			return swimsuit;
		}

		public String getSwim_split() {
			return swim_split;
		}

		public String getSwim_pos() {
			return swim_pos;
		}

		public String getSwim_def() {
			return Helper.calculateDuration(swim_split, best_swim_split, true, false);
		}

		public String getBest_swim_split() {
			return best_swim_split;
		}

		public String getRun_split() {
			return run_split;
		}

		public String getRun_pos() {
			return run_pos;
		}

		public String getRun_def() {
			return Helper.calculateDuration(run_split, best_run_split, true, false);
		}

		public String getBest_run_split() {
			return best_run_split;
		}

		public String getBike_split() {
			return bike_split;
		}

		public String getBike_pos() {
			return bike_pos;
		}

		public String getBike_def() {
			return Helper.calculateDuration(bike_split, best_bike_split, true, false);
		}

		public String getBest_bike_split() {
			return best_bike_split;
		}

		public Double getSwim_def_per() {
			return Helper.getPercentageByTime(best_swim_split, getSwim_def());
		}

		public Double getRun_def_per() {
			return Helper.getPercentageByTime(best_run_split, getRun_def());
		}

		public Double getBike_def_per() {
			return Helper.getPercentageByTime(best_bike_split, getBike_def());
		}

		public String getSwim_color() {
			return getColor(getSwim_split(), getSwim_def_per(), swim_cutoff);
		}

		public String getRun_color() {
			return getColor(getRun_split(), getRun_def_per(), run_cutoff);
		}

		public String getBike_color() {
			return getColor(getBike_split(), getBike_def_per(), bike_cutoff);
		}

		public String getSwim_cutoff() {
			return swim_cutoff;
		}

		public String getRun_cutoff() {
			return run_cutoff;
		}

		public String getBike_cutoff() {
			return bike_cutoff;
		}

		public String getBest_swimmer() {
			return best_swimmer;
		}

		public String getBest_runner() {
			return best_runner;
		}

		public String getBest_biker() {
			return best_biker;
		}

		public Timestamp getDate() {
			return date;
		}

		public static String getColor(String time, Double percent, String cutoff) {
			if(Helper.isEmpty(time)) return Constants.WHITE;
			if(Helper.isEmpty(cutoff)) return Constants.WHITE;
			if(cutoff.endsWith("%")) {
				// percent logic
				Integer cutoffPercent = null;
				try {
					cutoffPercent = Integer.valueOf(cutoff.substring(0, cutoff.length()-1));
				} catch (Exception ex) {}
				if(cutoffPercent==null || cutoffPercent==0) return Constants.WHITE;
				if(percent==null) return Constants.WHITE;
				// green
				if(percent<=cutoffPercent) return Constants.GREEN;
				return Constants.RED;
			} else {
				// time logic
				Integer cutoffSeconds = Helper.calculateSeconds(cutoff);
				if(cutoffSeconds==null || cutoffSeconds==0) return Constants.WHITE;
				Integer seconds = Helper.calculateSeconds(time);
				if(seconds==null || seconds==0) return Constants.WHITE;
				// green
				if(seconds<=cutoffSeconds) return Constants.GREEN;
				return Constants.RED;
			}
		}
	}
}
