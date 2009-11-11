package trimatrix.ui;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.Results;
import trimatrix.entities.CompetitionEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.CompetitionDetailUI}")

public class CompetitionDetailUI extends AEntityDetailUI implements Serializable {
	protected ValidValuesBinding countriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COUNTRY);
    public ValidValuesBinding getCountriesVvb() { return countriesVvb; }
    
    protected ValidValuesBinding m_compTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COMPTYPE);
    public ValidValuesBinding getCompTypesVvb() {return m_compTypesVvb;}
    
    public boolean isMyCompetition() {
    	if(entityDetailUI.getEntity()==Entity.SCOUTCOMPETITIONS) return true;
    	return false;
    }
    
    public boolean isTypeEnabled() {
    	Results result = new Results();
    	result.setCompetitionId(entity.getId());
    	List<Results> results = getDaoLayer().getResultsDAO().findByExample(result);
    	if((results==null||results.size()==0)&&getEnabled()) {
    		return true;    		
    	} else {
    		return false;
    	}
    }
    
	private Competitions entity;	
	private CompetitionsScouts entityCS;
    
	public CompetitionDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {CompetitionEntity.DESCRIPTION, CompetitionEntity.TYPE, CompetitionEntity.DATE}, true);
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
    	entity = (Competitions)entityObject;  
    	// set entity Competition Scout object
    	entityCS = ENTITYLISTLOGIC.getCompetitionScouts(entity.getId());
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
		entity.setDescription((String)values.get(CompetitionEntity.DESCRIPTION));
		entity.setType((String)values.get(CompetitionEntity.TYPE));
		entity.setDate((Date)values.get(CompetitionEntity.DATE));
		entity.setAddress((String)values.get(CompetitionEntity.ADDRESS));
		entity.setCountryKey((String)values.get(CompetitionEntity.COUNTRY));
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(CompetitionEntity.DESCRIPTION, entity.getDescription());		
		values.put(CompetitionEntity.TYPE, entity.getType());
		values.put(CompetitionEntity.DATE, entity.getDate());
		values.put(CompetitionEntity.ADDRESS, entity.getAddress());
		values.put(CompetitionEntity.COUNTRY, entity.getCountryKey());		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	/* 
	 * You just can edit your own competitions 
	 */
	@Override
	public boolean getEnabled() {
		return super.getEnabled() && ENTITYLISTLOGIC.isUserEqualUserLoggedOn(entity.getCreatedBy());
	}

	@Override
	public void prepareSave() {		
		if(entityDetailUI.entity==Entity.SCOUTCOMPETITIONS) {
			if(mode==Mode.NEW || mode==Mode.COPY) getLogic().getFunctionTreeLogic().createCompetitionScout(entity.getId());
		}
	}
	
	
}