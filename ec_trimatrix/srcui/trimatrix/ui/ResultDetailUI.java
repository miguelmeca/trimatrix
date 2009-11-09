package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.db.Results;
import trimatrix.entities.AttachmentEntity;
import trimatrix.entities.ResultEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.ResultDetailUI}")

public class ResultDetailUI extends AEntityDetailUI implements Serializable
{
    
	private Results entity;	
    
	public ResultDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {AttachmentEntity.DESCRIPTION, AttachmentEntity.CATEGORY, AttachmentEntity.FILENAME}, true);
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
    	// set fields
    	fillMaps();   
    	// set state
    	setState();
    }    

	public void validate() throws MandatoryCheckException, EmailNotValidException {		
		// mandatory check
		checkMandatory();
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		entity.setComment((String)values.get(ResultEntity.COMMENT));
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(ResultEntity.COMMENT, entity.getComment());
;
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	/**
	 * Call athlete selection pop up
	 * @param event
	 */
	public void onAthleteSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		entitySelectionUI.setEntity(Entity.MYSCOUTEDATHLETES);
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
}