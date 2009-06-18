package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.BasePopup;
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
import trimatrix.structures.SAuthorization;
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
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
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
        // a instance of MyWorkpage
        IWorkpage wp = getWorkpage();
        if (wp instanceof MyWorkpage) {
        	// parent bean
        	parentBean = ((MyWorkpage)wp).getParentBean();
        	// authorization
        	authorization = ((MyWorkpage)wp).getAuthorization();
        }
        // get mode 
        String strMode = getWorkpage().getParam(Constants.P_MODE);
        try {
        	mode = Constants.Mode.valueOf(strMode.toUpperCase());
        } catch (Exception ex) {
        	// if not set, switch to show mode
        	mode = Constants.Mode.SHOW;
        }   
        // if authorization is empty try from attribute Map
        if(authorization==null) {
        	String create = getWorkpage().getParam(Constants.CREATE);
    		String change = getWorkpage().getParam(Constants.CHANGE);
    		String delete = getWorkpage().getParam(Constants.DELETE);
    		if(create==null || !create.equals(Constants.TRUE)) create = Constants.FALSE;
    		if(change==null || !change.equals(Constants.TRUE)) change = Constants.FALSE;
    		if(delete==null || !delete.equals(Constants.TRUE)) delete = Constants.FALSE;
    		authorization = new SAuthorization(create, change, delete);
        }
        // change mode to set buttons
        changeMode(mode);               
        // set entity detail page 
        m_entityDetailPage = entity.getDetailPage().getUrl();
        // check if in new mode
    	if(mode == Constants.Mode.NEW) {
    		entityObject = ENTITYLISTLOGIC.create(entity);
    		id = entityObject.getId();
    		getWorkpage().setId(id);
        } else {          	
        	// catch NullPointerException if entity doesn't exist
        	try {
        		id = getWorkpage().getId();
            	// if id is not set, get id of own entity
        		if (id == null || id.length()==0) {
            		if (entity == Constants.Entity.USER) {
                    	id = getServiceLayer().getDictionaryService().getMyUser().getId();          	           
            		} else if (entity == Constants.Entity.PERSON) {
                    	id = getServiceLayer().getDictionaryService().getMyUser().getPerson().getId(); 
                    } 
            	}
        		// get entity
        		entityObject = ENTITYLISTLOGIC.get(entity, id);  
        		// set title of workpage
        		getWorkpage().setTitle(entityObject.toString());
            	if(entityObject==null) {
            		throw new NullPointerException();
            	}
        	} catch (NullPointerException npe) {
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
		YESNOPopup popup = YESNOPopup.createInstance(
				"Confirm deletion", 
				"Do you really want to delete the selected entity?", 
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {						
						if(ENTITYLISTLOGIC.delete(entity, id)) {							
							Statusbar.outputSuccess("Entity deleted");
							refreshParent();
							getWorkpageContainer().closeWorkpage(getWorkpage());
						} else {
							Statusbar.outputError("Entity could not be deleted!");
						}								
					}						
				}
		);	
		popup.getModalPopup().setLeft(BasePopup.POS_CENTER);
		popup.getModalPopup().setTop(BasePopup.POS_CENTER);
	}

	public void onSave(ActionEvent event) {
		// delegate to specific detail UI
		try {	
			entityDetailUI.validate();
			ENTITYLISTLOGIC.save(entity, entityObject);
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
			ENTITYLISTLOGIC.reload(entity, entityObject);   
			changeMode(Constants.Mode.SHOW);
			entityDetailUI.init();
		}		
	}
	
	public void onNew(ActionEvent event) {		
		// create separate workpage
		IWorkpageDispatcher wpd = getOwningDispatcher();
		IWorkpageContainer wpc = getWorkpageContainer();
		IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
				null, "New entity", null, true, parentBean, null);
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
			renderEditButton = false;
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
