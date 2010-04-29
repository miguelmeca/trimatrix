package trimatrix.ui;

import java.io.Serializable;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.Persons;
import trimatrix.db.Results;
import trimatrix.db.ResultsTria;
import trimatrix.entities.CompetitionEntity;
import trimatrix.entities.ResultEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.helper.Limit;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.ResultDetailUI}")

public class ResultDetailUI extends AEntityDetailUI implements Serializable {

	private static final String OVERALL = "overall";

	private Map<String, Limit> limitsMap;

	private String cutoffSwim;
	private String cutoffRun;
	private String cutoffBike;

	private Integer tolSwim;
	private Integer tolRun;
	private Integer tolBike;

	private Boolean swimsuit;
	public Boolean getSwimsuit() { return swimsuit; }

	private String bestSwim;
	public String getBestSwim() { return bestSwim; }

	private String bestRun;
	public String getBestRun() { return bestRun; }

	private String bestBike;
	public String getBestBike() { return bestBike; }

    public String getColorRun() {
    	return ResultEntity.Data.getColor((String)values.get(ResultEntity.RUN_SPLIT), getBestRun(), cutoffRun, tolRun);
    }

    public String getColorSwim() {
    	return ResultEntity.Data.getColor((String)values.get(ResultEntity.SWIM_SPLIT), getBestSwim(), cutoffSwim, tolSwim);
    }

    public String getColorBike() {
    	return ResultEntity.Data.getColor((String)values.get(ResultEntity.BIKE_SPLIT), getBestBike(), cutoffBike, tolBike);
    }

	protected final String[] MANDATORY_FIELDS_TRIA = new String[] {ResultEntity.CATEGORY_TRIA};

    public boolean isAdminView() {
    	return entityDetailUI.getEntity()==Entity.RESULT;
    }

    public boolean isTria() {
    	if (entity.getCompetition().getType() == null) return false;
    	return entity.getCompetition().getType().equals(CompetitionEntity.TRIATHLON) || entity.getCompetition().getType().equals(CompetitionEntity.XTERRA);
	}

	private Results entity;

	public ResultDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {ResultEntity.COMPETITION, ResultEntity.SCOUT, ResultEntity.ATHLETE}, true);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();
		entityDetailUI.setEntityDetailUI(this);
        // init data
        init(entityDetailUI.getEntityObject());
        // labeling
        setLabelRowDynamic();
    }

    public void init(Object entityObject) {
    	// set entity object
    	entity = (Results)entityObject;
    	// set enabled state and set fields
    	init();
    }

    public void init() {
    	// set state
    	setState();
    	// set scout
    	if(!isAdminView()) {
    		entity.setScout(getServiceLayer().getDictionaryService().getMyPerson());
    	}
    	// set fields
    	fillMaps();
    }

	public void validate() throws MandatoryCheckException, EmailNotValidException {
		// mandatory check
		checkMandatory();
		// ergo
		if (isTria()) {
			checkMandatory(MANDATORY_FIELDS_TRIA);
		}
		// fill values to entities properties
		fillEntityProperties();
	}

	private void fillEntityProperties() {
		entity.setFinalPosition((String)values.get(ResultEntity.FINALPOSITION));
		entity.setTime((String)values.get(ResultEntity.TIME));
		entity.setComment((String)values.get(ResultEntity.COMMENT));
		// tria
		if(isTria()) {
			ResultsTria tria = entity.getResultsTria();
			tria.setCategory((String)values.get(ResultEntity.CATEGORY_TRIA));
			tria.setSwimSplit((String)values.get(ResultEntity.SWIM_SPLIT));
			tria.setRunSplit((String)values.get(ResultEntity.RUN_SPLIT));
			tria.setBikeSplit((String)values.get(ResultEntity.BIKE_SPLIT));
			tria.setSwimPosition((String)values.get(ResultEntity.SWIM_POSITION));
			tria.setRunPosition((String)values.get(ResultEntity.RUN_POSITION));
			tria.setBikePosition((String)values.get(ResultEntity.BIKE_POSITION));
		}
	}

	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(ResultEntity.FINALPOSITION, entity.getFinalPosition());
		values.put(ResultEntity.TIME, entity.getTime());
		values.put(ResultEntity.COMMENT, entity.getComment());
		setCompetitionDescription(entity);
		setScoutDescription(entity);
		setAthleteDescription(entity);
		// tria
		ResultsTria tria = entity.getResultsTria();
		if(tria!=null) {
			values.put(ResultEntity.CATEGORY_TRIA, tria.getCategory());
			values.put(ResultEntity.SWIM_SPLIT, tria.getSwimSplit());
			values.put(ResultEntity.RUN_SPLIT, tria.getRunSplit());
			values.put(ResultEntity.BIKE_SPLIT, tria.getBikeSplit());
			values.put(ResultEntity.SWIM_POSITION, tria.getSwimPosition());
			values.put(ResultEntity.RUN_POSITION, tria.getRunPosition());
			values.put(ResultEntity.BIKE_POSITION, tria.getBikePosition());
			//values.put(ResultEntity.SWIMSUIT, tria.getSwimsuit());
		}
		// limits
    	setLimits();

		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}
		// mandatory fields for tria result
		for (String field : MANDATORY_FIELDS_TRIA) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
	}

	public void onTimeFlush(ActionEvent event) {
		// get clientname to separate by source
		String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
		String field;
		if(ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
			field = ResultEntity.SWIM_SPLIT;
		} else if(ResultEntity.RUN.equalsIgnoreCase(clientname)) {
			field = ResultEntity.RUN_SPLIT;
		} else if(ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
			field = ResultEntity.BIKE_SPLIT;
		} else if(OVERALL.equalsIgnoreCase(clientname)) {
			field = ResultEntity.TIME;
		} else {
			return;
		}
		// correct input
		String input = (String)values.get(field);
		String corrInput = Helper.correctTimeInput(input);
		// if null don't write back the value
		if(corrInput==null) return;
		values.put(field, corrInput);
	}

	/**
	 * Call competition selection pop up
	 * @param event
	 */
	public void onCompetitionSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.COMPETITION);
		// restrict if not in admin view
		if(isAdminView()) {
			entitySelectionUI.buildData(Entity.COMPETITION);
		} else {
			entitySelectionUI.buildData(Entity.SCOUTCOMPETITIONS);
		}
       	entitySelectionUI.prepareCallback(new ISelectionCallback(){
			public void cancel() {
				m_popup.close();
			}
			public void selected(String id) {
				Competitions competition = (Competitions)ENTITYLISTLOGIC.get(Constants.Entity.COMPETITION, id);
				entity.setCompetition(competition);
				setCompetitionDescription(entity);
				setLimits();
				m_popup.close();
			}});
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.COMPETITIONSELECTION.getUrl(), "Wettkampfsuche", 800, 600, this);
    }

	/**
	 * Get description for selected competition
	 * @param competition
	 */
	private void setCompetitionDescription(Results result) {
		Competitions competition = result.getCompetition();
		String competitionDescription = Constants.EMPTY;
		if (competition!=null) {
			competitionDescription = competition.toString();
		}
		values.put(ResultEntity.COMPETITION, competitionDescription);
	}

	/**
	 * Call scout selection pop up
	 * Only in admin view available
	 * @param event
	 */
	public void onScoutSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		entitySelectionUI.buildData(Entity.SCOUTS);
       	entitySelectionUI.prepareCallback(new ISelectionCallback(){
			public void cancel() {
				m_popup.close();
			}
			public void selected(String id) {
				Persons person = (Persons)ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				entity.setScout(person);
				setScoutDescription(entity);
				m_popup.close();
			}});
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), "Scoutsuche", 800, 600, this);
    }

	/**
	 * Get description for selected scout
	 * @param scout
	 */
	private void setScoutDescription(Results result) {
		Persons scout = result.getScout();
		String scoutDescription = Constants.EMPTY;
		if (scout!=null) {
			scoutDescription = scout.toString();
		}
		values.put(ResultEntity.SCOUT, scoutDescription);
	}

	/**
	 * Call athlete selection pop up
	 * @param event
	 */
	public void onAthleteSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		// restrict if not in admin view
		if(isAdminView()) {
			entitySelectionUI.buildData(Entity.ATHLETES);
		} else {
			entitySelectionUI.buildData(Entity.MYSCOUTEDATHLETES);
		}
       	entitySelectionUI.prepareCallback(new ISelectionCallback(){
			public void cancel() {
				m_popup.close();
			}
			public void selected(String id) {
				Persons person = (Persons)ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				entity.setAthlete(person);
				setAthleteDescription(entity);
				m_popup.close();
			}});
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), "Athletensuche", 800, 600, this);
    }

	/**
	 * Get description for selected athlete
	 * @param athlete
	 */
	private void setAthleteDescription(Results result) {
		Persons athlete = result.getAthlete();
		String athleteDescription = Constants.EMPTY;
		if (athlete!=null) {
			athleteDescription = athlete.toString();
		}
		values.put(ResultEntity.ATHLETE, athleteDescription);
	}

	public void onCompetitionClicked(ActionEvent event) {
		if(entity.getCompetition()==null) return;
		loadEntityDetailPage(Entity.COMPETITION, entity.getCompetition().getId(), entity.getCompetition().toString());
	}

	public void onAthleteClicked(ActionEvent event) {
		if(entity.getAthlete()==null) return;
		loadEntityDetailPage(Entity.PERSON, entity.getAthlete().getId(), entity.getAthlete().toString());
	}

	public void onScoutClicked(ActionEvent event) {
		if(entity.getScout()==null) return;
		loadEntityDetailPage(Entity.PERSON, entity.getScout().getId(), entity.getScout().toString());
	}

	public Double getPercentDeficitSwim() {
		return Helper.getPercentageByTime(getBestSwim(), getSwimDeficit());
	}

	public Double getPercentDeficitRun() {
		return Helper.getPercentageByTime(getBestRun(), getRunDeficit());
	}

	public Double getPercentDeficitBike() {
		return Helper.getPercentageByTime(getBestBike(), getBikeDeficit());
	}

	public void onCategoryF4(ActionEvent event) {
		IdTextSelection idts = IdTextSelection.createInstance();
		for (String category : limitsMap.keySet()) {
			idts.addLine(category, "");
		}
		idts.setCallBack(new ISetId() {
			public void setId(String id) {
				values.put(ResultEntity.CATEGORY_TRIA, id);
				// refresh limits
				setLimits();
			}
		});
		idts.setWithHeader(false);
		idts.setSuppressHeadline(true);
		idts.setRenderTextColumn(false);
		idts.setPopupWidth(120);
		idts.setPopupHeight(100);
	}

	@Override
	public void prepareSave() {
		// ergo
		if (entity.getResultsTria() == null && isTria()) {
			// create tria
			ResultsTria tria = new ResultsTria(entity.getId());
			entity.setResultsTria(tria);
			// delete others
			return;
		}
		// others
	}

	/**
	 * Set limits, same logic as for ResultEntity there the info is set in
	 * SQLExecutorService and Data class
	 */
	private void setLimits() {
		if(entity==null) return;
		if(entity.getCompetitionId()==null || entity.getScoutId()==null) return;
		CompetitionsScouts entityCS = ENTITYLISTLOGIC.getCompetitionScouts(entity.getCompetitionId(), entity.getScoutId());
		if(entityCS==null) return;
		limitsMap = getLogic().getCompetitionLogic().getLimitsMap(entityCS.getLimits());
		// tria
		if(isTria()) {
			Limit limits = limitsMap.get((String)values.get(ResultEntity.CATEGORY_TRIA));
			if(limits!=null) {
				cutoffSwim = limits.getLimits()[0];
				cutoffRun = limits.getLimits()[1];
				tolSwim = limits.getTolerances()[0];
				tolRun = limits.getTolerances()[1];
				bestSwim = limits.getSwim()[1];
				bestRun = limits.getRun()[1];
				swimsuit = limits.getSwimsuit();
				// TODO: this was added after first release
				if(limits.getLimits().length>2) {
					cutoffBike = limits.getLimits()[2];
					tolBike = limits.getTolerances()[2];
					bestBike = limits.getBike()[1];
				}
			}
		}
	}

	public String getSwimDeficit() {
		return Helper.calculateDuration((String)values.get(ResultEntity.SWIM_SPLIT), bestSwim, true, false);
	}

	public String getRunDeficit() {
		return Helper.calculateDuration((String)values.get(ResultEntity.RUN_SPLIT), bestRun, true, false);
	}

	public String getBikeDeficit() {
		return Helper.calculateDuration((String)values.get(ResultEntity.BIKE_SPLIT), bestBike, true, false);
	}
}