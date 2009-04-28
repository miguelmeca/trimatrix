package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
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
	
	private Object parentBean;
	
	private String id;
	public String getId() { return id; }
	
	private Constants.Mode mode;
	public Constants.Mode getMode() { return mode; }	
	
	protected boolean renderSaveButton;
	public boolean getRenderSaveButton() { return renderSaveButton; }
	
	protected boolean renderEditButton;
	public boolean getRenderEditButton() { return renderEditButton; }
	
	protected boolean renderCancelButton;
	public boolean getRenderCancelButton() { return renderCancelButton; }
	
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
        // parent bean
        IWorkpage wp = getWorkpage();
        if (wp instanceof MyWorkpage) {
        	parentBean = ((MyWorkpage)wp).getParentBean();
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
        	entityObject = ENTITYLISTLOGIC.getEntity(entity, id);   
        	if(entityObject==null) {
        		Statusbar.outputError("Entity doesn't exist!", "Maybe the entity is marked as deleted!");
            	getWorkpageContainer().closeWorkpage(getWorkpage());
        	}
        }     	
	}
	
	private void refreshParent() {		
		if(parentBean instanceof EntityListUI) {
			EntityListUI entityListUI = (EntityListUI)parentBean;
			entityListUI.onRefresh(null);
			Dictionary.logger.info("Bean EntityDetailUI : parent bean refreshed!");
		} else {
			Dictionary.logger.info("Bean EntityDetailUI : no parent bean to refresh!");
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
							refreshParent();
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
			refreshParent();
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
	
	public void onEdit(ActionEvent event) {
		changeMode(Constants.Mode.CHANGE);
		entityDetailUI.init();
	}
	
	public void onCancel(ActionEvent event) {
		// when called in save mode, close page
		if (mode == Constants.Mode.NEW) {
			getWorkpageContainer().closeWorkpage(getWorkpage());
		} else {
			ENTITYLISTLOGIC.reloadEntity(entity, entityObject);   
			changeMode(Constants.Mode.SHOW);
			entityDetailUI.init();
		}		
	}
	
	public void onNew(ActionEvent event) {		
		// create separate workpage
		IWorkpageDispatcher wpd = getOwningDispatcher();
		IWorkpageContainer wpc = getWorkpageContainer();
		IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.url(),
				null, "New entity", null, true, parentBean);
		wp.setParam(Constants.P_ENTITY, entity.name());
		wp.setParam(Constants.P_MODE, Constants.Mode.NEW.name());
		wpc.addWorkpage(wp);
	}
	
	private void changeMode(Constants.Mode mode) {
		this.mode = mode;
		if (mode == Constants.Mode.CHANGE) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = false;
			renderSaveButton = true;
			renderCancelButton = true;
			
		} 
		if (mode == Constants.Mode.SHOW) {
			renderNewButton = true;
			renderDeleteButton = true;
			renderEditButton = true;
			renderSaveButton = false;
			renderCancelButton = false;			
		}		
		if (mode == Constants.Mode.NEW) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = true;
			renderSaveButton = true;
			renderCancelButton = true;
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
