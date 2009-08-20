package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Tests;
import trimatrix.entities.TestEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.TestDetailUI}")

public class TestDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{    
	private Tests entity;	
	
	public TestDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {TestEntity.PERSON, TestEntity.TYPE, TestEntity.DOCTOR, TestEntity.DATE}, true);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
        // labeling
        setLabelRowDynamic();
	}
	
	protected ValidValuesBinding m_testTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.TESTTYPE);
    public ValidValuesBinding getTestTypesVvb() { return m_testTypesVvb; }
	
    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Tests)entityObject;        	 	
    	// set enabled state and set fields
    	init();		
	}
	
    public void init() {
    	// set fields
    	fillMaps();   
    	// set state
    	setState();		
	}
	
    public void validate() throws MandatoryCheckException,
			EmailNotValidException {
    	// mandatory check
		checkMandatory();
		// fill values to entities properties
		fillEntityProperties();
		
	}
    
    private void fillEntityProperties() {
		// detail
		entity.setDescription((String)values.get(TestEntity.DESCRIPTION));
		Timestamp timestamp = null;
		Date date = (Date)values.get(TestEntity.DATE);
		if (date!=null) {
			timestamp = new Timestamp(date.getTime());
		} 
		entity.setDate(timestamp);
		entity.setType((String)values.get(TestEntity.TYPE));
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(TestEntity.DESCRIPTION, entity.getDescription());
		Date date = null;
		Timestamp timestamp = entity.getDate();
		if (timestamp!=null) {
			date =  new Date(timestamp.getTime());
		} 
		values.put(TestEntity.DATE, date);
		values.put(TestEntity.TYPE, entity.getType());
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}

}
