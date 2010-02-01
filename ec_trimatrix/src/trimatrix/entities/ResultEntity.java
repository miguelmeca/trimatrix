package trimatrix.entities;

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
    public static final String COMPETITION = "competition";
    public static final String ATHLETE = "athlete";
    public static final String SCOUT = "scout";
    public static final String FINALPOSITION = "final_position";
    public static final String TIME = "time";

    // Constants Triathlon
    public static final String CATEGORY_TRIA = "category_tria";
    public static final String SWIM_SPLIT = "swim_split";
    public static final String RUN_SPLIT = "run_split";
    public static final String SWIM_POSITION = "swim_pos";
    public static final String RUN_POSITION = "run_pos";
    public static final String BEST_SWIM_SPLIT = "best_swim_split";
    public static final String BEST_RUN_SPLIT = "best_run_split";
    public static final String SWIM_DEFICIT = "swim_def";
    public static final String RUN_DEFICIT = "run_def";
    public static final String SWIM_DEFICIT_PER = "swim_def_per";
    public static final String RUN_DEFICIT_PER = "run_def_per";
    public static final String SWIM_COLOR = "swim_color";
    public static final String RUN_COLOR = "run_color";
    public static final String SWIMSUIT = "swimsuit";

    // Constants for SGRIDMETADATA
    public static final String META_SWIM = "<t:formattedfield value='.{datum." + SWIM_DEFICIT_PER + "}' background='.{datum." + SWIM_COLOR + "}'  format='double' enabled='false' width='50' align='center'/>";
    public static final String META_RUN = "<t:formattedfield value='.{datum." + RUN_DEFICIT_PER + "}' background='.{datum." + RUN_COLOR + "}'  format='double' enabled='false' width='50' align='center'/>";

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

	public List<SGridMetaData> getGridMetaData(String filter) {
		// get general Meta Data
		List<SGridMetaData> gridMetaData = getGridMetaData();
		// add specific data
		if(CompetitionEntity.TRIATHLON.equalsIgnoreCase(filter)) {
			gridMetaData.add(new SGridMetaData("#{rr.literals.category}",CATEGORY_TRIA, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.swimsuit}",SWIMSUIT, SGridMetaData.Component.CHECKBOX));
			gridMetaData.add(new SGridMetaData("#{rr.literals.swim_split}",SWIM_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.ranking_swim}",SWIM_POSITION, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_swim}",SWIM_DEFICIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.best_swim_split}",BEST_SWIM_SPLIT, SGridMetaData.Component.FIELD));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_percent}",SWIM_DEFICIT_PER, SGridMetaData.Component.FORMATED_DOUBLE));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.swim}",SWIM_COLOR, SGridMetaData.Component.COLORFIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.swim}",SWIM_COLOR, META_SWIM));
			gridMetaData.add(new SGridMetaData("#{rr.literals.run_split}",RUN_SPLIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.ranking_run}",RUN_POSITION, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_run}",RUN_DEFICIT, SGridMetaData.Component.FIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.best_run_split}",BEST_RUN_SPLIT, SGridMetaData.Component.FIELD));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.deficit_percent}",RUN_DEFICIT_PER, SGridMetaData.Component.FORMATED_DOUBLE));
			//gridMetaData.add(new SGridMetaData("#{rr.literals.run}",RUN_COLOR, SGridMetaData.Component.COLORFIELD));
			gridMetaData.add(new SGridMetaData("#{rr.literals.run}",RUN_COLOR, META_RUN));
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
        	return sqlExecutorService.getResultEntities(null, null, dictionaryService.getMyPerson().getId(), null, filter);
        } else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	/* (non-Javadoc)
	 * @see trimatrix.entities.IEntity#getData(trimatrix.utils.Constants.Entity, java.lang.String)
	 */
	public List<IEntityData> getData(Constants.Entity entity, String personId, String filter) {
		if (entity == Constants.Entity.MYRESULTS) {
        	return sqlExecutorService.getResultEntities(null, null, dictionaryService.getMyPerson().getId(), personId, filter);
        }  else {
        	return Constants.EMPTYENTITYDATA;
        }
	}

	@Override
	public IEntityData getData(String id) {
		List<IEntityData> result = sqlExecutorService.getResultEntities(id, null, null, null, null);
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
	public Report getPrintReport(Entity entity, List<IEntityData> data) {
		if (entity == Constants.Entity.MYRESULTS) {
			PerformanceChart.Data reportData = new PerformanceChart.Data();
			for(IEntityData entityData : data) {
				ResultEntity.Data resultData = (ResultEntity.Data)entityData;
				if(reportData.name==null) reportData.name = resultData.athlete;
				PerformanceChart.Data.Item item = new PerformanceChart.Data.Item();
				item.position = resultData.final_position;
				reportData.items.add(item);
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
		public String competition;
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
		public String swim_color;
		public String run_color;
		public String green_high;
		public String red_low;

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

		public boolean getSwimsuit() {
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

		public Double getSwim_def_per() {
			return Helper.getPercentageByTime(best_swim_split, getSwim_def());
		}

		public Double getRun_def_per() {
			return Helper.getPercentageByTime(best_run_split, getRun_def());
		}

		public String getSwim_color() {
			return getColor(getSwim_split());
		}

//		public String getRun_color() {
//			return getColor(getRun_def_per());
//		}

		private String getColor(String time) {
			Integer greenSeconds = Helper.calculateSeconds(green_high);
			Integer redSeconds = Helper.calculateSeconds(red_low);
			Integer seconds = Helper.calculateSeconds(time);
			if(seconds==null) return Constants.WHITE;
			// green
			if(greenSeconds!=null && greenSeconds>0 && seconds<greenSeconds) return Constants.GREEN;
			// red
			if(redSeconds!=null && redSeconds>0 && seconds>redSeconds) return Constants.RED;
			// unspecified
			if(green_high==null && red_low==null) return Constants.WHITE;
			// yellow
			return Constants.YELLOW;
		}
	}
}
