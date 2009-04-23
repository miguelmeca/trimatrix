package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.entities.IEntityObject;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.EntityDetailUI}")
public class EntityDetailUI extends MyWorkpageDispatchedBean implements
		Serializable {

	private final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
	private Constants.Entity entity;
	private IEntityDetailUI entityDetailUI;
	
	private IEntityObject entityObject;
	public Object getEntityObject() { return entityObject; }
	public void setEntityObject(IEntityObject entityObject) { this.entityObject = entityObject; }
	
	private String id;
	public String getId() { return id; }
	
	private Constants.Mode mode;
	public Constants.Mode getMode() { return mode; }	
	
	private String txtEditButton;	
	public String getTxtEditButton() { return txtEditButton; }
	
	protected boolean renderSaveButton;
	public boolean getRenderSaveButton() { return renderSaveButton; }
	
	protected boolean renderEditButton;
	public boolean getRenderEditButton() { return renderEditButton; }
	
	protected boolean renderDeleteButton;
	public boolean getRenderDeleteButton() { return renderDeleteButton; }
	
	protected boolean renderNewButton;
	public boolean getRenderNewButton() { return renderNewButton; }

	public EntityDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get entity
        String strEntity = getWorkpage().getParam(Constants.P_ENTITY);
        try {
        	entity = Constants.Entity.valueOf(strEntity.toUpperCase());
        } catch (Exception ex) {
        	Statusbar.outputError("No or wrong entity set", "For list view processing an entity has to be set by the functiontreenode!");
        	getWorkpageContainer().closeWorkpage(getWorkpage());
        }
        // get mode 
        String strMode = getWorkpage().getParam(Constants.P_MODE);
        try {
        	mode = Constants.Mode.valueOf(strMode.toUpperCase());
        } catch (Exception ex) {
        	Statusbar.outputError("Not defined mode set!");
        	getWorkpageContainer().closeWorkpage(getWorkpage());
        }   
        // change mode to set buttons
        changeMode(mode);               
        // set entity detail page 
        if (entity == Constants.Entity.USER) {
        	m_entityDetailPage = Constants.Page.USERDETAIL.url();           	           
        } else if (entity == Constants.Entity.PERSON) {
        	m_entityDetailPage = Constants.Page.PERSONDETAIL.url(); 
        }              
        // get/create entity object and set ID
    	if(mode == Constants.Mode.NEW) {
    		entityObject = ENTITYLISTLOGIC.createEntity(entity);
    		id = entityObject.getId();
    		getWorkpage().setId(id);
        } else {  
        	id = getWorkpage().getId();
        	// TODO move to Service Layer
        	entityObject = ENTITYLISTLOGIC.getEntity(entity, id);   
        	if(entityObject==null) {
        		Statusbar.outputError("Entity doesn't exist!", "Maybe the entity is marked as deleted!");
            	getWorkpageContainer().closeWorkpage(getWorkpage());
        	}
        }     	
	}
	
	private void refreshListUI() {
		Dictionary.logger.info("Bean EntityDetailUI : entityListUI Bean is set!");
		MyWorkpage wp = (MyWorkpage)getWorkpage();
		Object bean = wp.getParentBean();
		if(bean instanceof EntityListUI) {
			EntityListUI entityListUI = (EntityListUI)bean;
			entityListUI.onRefresh(null);
		} else {
			Dictionary.logger.info("Bean EntityDetailUI : entityListUI Bean is null!");
		}
	}
	
	public void setEntityDetailUI(IEntityDetailUI entityDetailUI){
        this.entityDetailUI = entityDetailUI;
	}

	public void onDelete(ActionEvent event) {
		YESNOPopup.createInstance(
				"Confirm deletion", 
				"Do you really want to delete the selected entity?", 
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {						
						if(ENTITYLISTLOGIC.deleteEntity(entity, id)) {							
							Statusbar.outputSuccess("Entity deleted");
							refreshListUI();
							getWorkpageContainer().closeWorkpage(getWorkpage());
						} else {
							Statusbar.outputError("Entity could not be deleted!");
						}								
					}						
				}
		);						
	}

	public void onSave(ActionEvent event) {
		// delegate to specific detail UI
		try {	
			entityDetailUI.validate();
			ENTITYLISTLOGIC.saveEntity(entity, entityObject);
			getWorkpage().setTitle(entityObject.toString());
			Statusbar.outputSuccess("Entity saved");
			refreshListUI();
			changeMode(Constants.Mode.SHOW);
			entityDetailUI.init();
		} catch (MandatoryCheckException mce) {
			Statusbar.outputError("Not all mandatory fields filled", "Value for field " + mce.getField() + " missing!" );
		} catch (EmailNotValidException env) {
			Statusbar.outputError("Email is not valid", "The email address " + env.getEmail() + " is not valid!" );
		} catch (DataIntegrityViolationException dive) {
			Statusbar.outputError("Entity could not be saved (Data Integrity)", dive.getRootCause().toString());
		} catch (Exception ex){			
			Statusbar.outputError("Entity could not be saved", ex.toString());			
		} 	
	}
	
	public void onEditShow(ActionEvent event) {
		switchMode();
		//entityObject = ENTITYLISTLOGIC.getEntity(entity, id); 
		//entityDetailUI.init(entityObject);	
		entityDetailUI.init();
	}
	
	public void onNew(ActionEvent event) {		
		entityObject = ENTITYLISTLOGIC.createEntity(entity);
		// first set mode, because init method pulls mode from this bean
		changeMode(Constants.Mode.NEW);
		getWorkpage().setTitle("New");
		entityDetailUI.init(entityObject);		
	}
	
	private void changeMode(Constants.Mode mode) {
		this.mode = mode;
		if (mode == Constants.Mode.CHANGE) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = true;
			renderSaveButton = true;
			txtEditButton = Dictionary.getValueFromExpression("#{rr.literals.cancel}");//"Abbrechen";//"#{rr.literals.cancel}";
		} 
		if (mode == Constants.Mode.SHOW) {
			renderNewButton = true;
			renderDeleteButton = true;
			renderEditButton = true;
			renderSaveButton = false;
			txtEditButton = Dictionary.getValueFromExpression("#{rr.literals.edit}");//"�ndern"; //"#{rr.literals.edit}";
		}		
		if (mode == Constants.Mode.NEW) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = false;
			renderSaveButton = true;
		}
	}
	
	private void switchMode() {
		if (mode == Constants.Mode.CHANGE) {
			changeMode(Constants.Mode.SHOW);
		} else {
			changeMode(Constants.Mode.CHANGE);
		}
	}

	protected String m_entityDetailPage;

	public String getEntityDetailPage() {
		return m_entityDetailPage;
	}

	public void setEntityDetailPage(String value) {
		m_entityDetailPage = value;
	}

}
