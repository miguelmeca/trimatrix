package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.db.Persons;
import trimatrix.db.Results;
import trimatrix.entities.ResultEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.ResultDetailUI}")

public class ResultDetailUI extends AEntityDetailUI implements Serializable
{
    public boolean isAdminView() {
    	return entityDetailUI.getEntity()==Entity.RESULT;
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
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		entity.setFinalPosition((String)values.get(ResultEntity.FINALPOSITION));
		entity.setTime((String)values.get(ResultEntity.TIME));
		entity.setComment((String)values.get(ResultEntity.COMMENT));
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
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
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
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Competitions competition = (Competitions)ENTITYLISTLOGIC.get(Constants.Entity.COMPETITION, id);
				entity.setCompetition(competition);
				setCompetitionDescription(entity);	
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
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
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
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
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
		Statusbar.outputMessage("Competition clicked");
	}
	
	public void onAthleteClicked(ActionEvent event) {
		Statusbar.outputMessage("Athlete clicked");
	}
	
	public void onScoutClicked(ActionEvent event) {
		Statusbar.outputMessage("Scout clicked");
	}
}