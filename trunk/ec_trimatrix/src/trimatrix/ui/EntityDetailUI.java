package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
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
import trimatrix.exceptions.EntityLockedException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.EntityListLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.LockManager;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.EntityDetailUI}")
public class EntityDetailUI extends MyWorkpageDispatchedBean implements
		Serializable {

	private final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
	protected Constants.Entity entity;
	public Constants.Entity getEntity() { return entity; }

	protected String m_entityDetailPage;
	public String getEntityDetailPage() {return m_entityDetailPage;}

	private IEntityDetailUI entityDetailUI;

	private IEntityObject entityObject;
	public Object getEntityObject() { return entityObject; }
	public void setEntityObject(IEntityObject entityObject) { this.entityObject = entityObject; }

	private Object parentBean;

	private boolean copyable;

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

	protected boolean renderCopyButton;
	public boolean getRenderCopyButton() { return renderCopyButton; }

	public EntityDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get entity
        String strEntity = getWorkpage().getParam(Constants.P_ENTITY);
        try {
        	entity = Constants.Entity.valueOf(strEntity.toUpperCase());
        } catch (Exception ex) {
        	//Statusbar.outputAlert(Helper.getMessages("entity_wrong"), Helper.getLiteral("error"), Helper.getMessages("entity_wrong_detail")).setLeftTopReferenceCentered();
        	Logger.getRootLogger().error(Helper.getMessages("entity_wrong"));
        	getWorkpageContainer().closeWorkpage(getWorkpage());
        }
        // a instance of MyWorkpage
        IWorkpage wp = getWorkpage();
        if (wp instanceof MyWorkpage) {
        	// parent bean
        	parentBean = ((MyWorkpage)wp).getParentBean();
        	// authorization
        	authorization = ((MyWorkpage)wp).getAuthorization();
        	// entity object
        	entityObject = ((MyWorkpage)wp).getEntityObject();
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
        switch (mode) {
		case NEW:
			entityObject = ENTITYLISTLOGIC.create(entity);
    		id = entityObject.getId();
    		// check global lock
    		try {
    			LockManager.lockEntry(id);
    		} catch (Exception ex) {
    			// this should never happen!
    			Statusbar.outputAlert(Helper.getMessages("entity_lock"), Helper.getLiteral("warn"), ex.toString()).setLeftTopReferenceCentered();
    		}
    		getWorkpage().setId(id);
			break;
		case COPY:
			// set title of workpage
			id = getWorkpage().getId();
    		getWorkpage().setTitle(entityObject.toString());
    		// check global lock
    		try {
    			LockManager.lockEntry(id);
    		} catch (Exception ex) {
    			// this should never happen!
    			Statusbar.outputAlert(Helper.getMessages("entity_lock"), Helper.getLiteral("warn"), ex.toString()).setLeftTopReferenceCentered();
    		}
    		break;
		default:
			// catch NullPointerException if entity doesn't exist
        	try {
        		// the whole logic also has to be implemented in MyWorkpageDispagedBean Label handling!!
        		id = getWorkpage().getId();
        		// if id is not set, get id of own entity
            	if (id == null || id.length()==0) { throw new NullPointerException(); }
            	// remove immutability mark
            	id = id.replace(Constants.FINAL, Constants.EMPTY);
        		// special handling for competitions
    			// because page ID has to be unique to resolve right page
    			// set in EntityListUI
       			String[] arrId = id.split("#");
        		if(arrId!=null && arrId.length==2) id = arrId[1];
        		// if id is not set, get id of own entity
            	if (id == null || id.length()==0) { throw new NullPointerException(); }
                // check if it's not the ID
            	if (Constants.Entity.USER.name().equalsIgnoreCase(id)) {
                   	id = getServiceLayer().getDictionaryService().getMyUser().getId();
                } else if (Constants.Entity.PERSON.name().equalsIgnoreCase(id)) {
                   	id = getServiceLayer().getDictionaryService().getMyUser().getPerson().getId();
                }
            	// get entity
            	entityObject = ENTITYLISTLOGIC.get(entity, id);
        		// set title of workpage
        		getWorkpage().setTitle(entityObject.toString());
        	} catch (NullPointerException npe) {
        		Statusbar.outputAlert(Helper.getMessages("entity_not_exist"), Helper.getLiteral("warn"), Helper.getMessages("entity_deleted")).setLeftTopReferenceCentered();
            	// TODO close workpage or switch to another
        	}
			break;
		}
        // set if copyable
        copyable = ENTITYLISTLOGIC.isCopyable(entity, entityObject);
	}

	private void refreshParent() {
		if(parentBean instanceof EntityListUI) {
			EntityListUI entityListUI = (EntityListUI)parentBean;
			entityListUI.onRefresh(null);
			logger.info("Bean EntityDetailUI : parent bean refreshed!");
		} else {
			logger.info("Bean EntityDetailUI : no parent bean to refresh!");
		}
	}

	public void setEntityDetailUI(IEntityDetailUI entityDetailUI){
        this.entityDetailUI = entityDetailUI;
	}

	public void onDelete(ActionEvent event) {
		YESNOPopup popup = YESNOPopup.createInstance(
				String.format(Helper.getMessages("confirm_delete_detail"), entity.getDescription()),
                Helper.getMessages("confirm_delete"),
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {
						boolean isDeleted = ENTITYLISTLOGIC.delete(entity, id);
						if(isDeleted) {
							entityDetailUI.postDelete(true);
							Statusbar.outputSuccess(Helper.getMessages("delete_success"));
							refreshParent();
							// refresh beans
					        getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(entity));
					        // close page
					        getWorkpageContainer().closeWorkpage(getWorkpage());
						} else {
							entityDetailUI.postDelete(false);
							Statusbar.outputError(Helper.getMessages("delete_failure"));
						}
					}
				}
		);
		popup.getModalPopup().setLeftTopReferenceCentered();
	}

	public void onSave(ActionEvent event) {
		// delegate to specific detail UI
		try {
			entityDetailUI.prepareSave();
			entityDetailUI.validate();
			entityObject = ENTITYLISTLOGIC.save(entity, entityObject);
			entityDetailUI.postSave();
			getWorkpage().setTitle(entityObject.toString());
			Statusbar.outputSuccess(Helper.getMessages("save_success"));
			//refreshParent();
			// unlock entity
			LockManager.unlockEntity(id);
			changeMode(Constants.Mode.SHOW);
			entityDetailUI.init(entityObject);
			// refresh beans
	        getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(entity));

		} catch (MandatoryCheckException mce) {
			Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn"), String.format(Helper.getMessages("field_missing"), mce.getField()) ).setLeftTopReferenceCentered();
		} catch (EmailNotValidException env) {
			Statusbar.outputAlert(Helper.getMessages("email_invalid"), Helper.getLiteral("error"), String.format(Helper.getMessages("email_invalid_detail"),env.getEmail())).setLeftTopReferenceCentered();
		} catch (DataIntegrityViolationException dive) {
			Statusbar.outputAlert(Helper.getMessages("save_failure"), Helper.getLiteral("error"), dive.getRootCause().toString()).setLeftTopReferenceCentered();
		} catch (Exception ex){
			Statusbar.outputAlert(Helper.getMessages("save_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	public void onEdit(ActionEvent event) {
		if(!entityDetailUI.checkEdit()) return;
		// check global lock
		try {
			LockManager.lockEntry(id);
		} catch (EntityLockedException ele) {
			Statusbar.outputAlert(Helper.getMessages("entity_lock"), Helper.getLiteral("warn"), String.format(Helper.getMessages("entity_lock_detail"), ele.getUser())).setLeftTopReferenceCentered();
			return;
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("entity_lock"), Helper.getLiteral("warn"), ex.toString()).setLeftTopReferenceCentered();
			return;
		}
		changeMode(Constants.Mode.CHANGE);
		// reload because of optimistic lock problem with referenced objects
		ENTITYLISTLOGIC.reload(entity, entityObject);
		entityDetailUI.init();
	}

	public void onCancel(ActionEvent event) {
		// when called in save mode, close page
		if (mode == Constants.Mode.NEW ||
			mode == Constants.Mode.COPY	||
			mode == Constants.Mode.SINGLECHANGE) {
			// set mode back so the close handler will not be invoked
			changeMode(Constants.Mode.SHOW);
			getWorkpageContainer().closeWorkpage(getWorkpage());
		} else {
			ENTITYLISTLOGIC.reload(entity, entityObject);
			changeMode(Constants.Mode.SHOW);
			entityDetailUI.init();
		}
		// unlock entity
		LockManager.unlockEntity(id);
	}

	public void onNew(ActionEvent event) {
		// create separate workpage
		IWorkpageDispatcher wpd = getOwningDispatcher();
		IWorkpageContainer wpc = getWorkpageContainer();
		IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
				null, "New entity", null, true, parentBean, null, null);
		wp.setParam(Constants.P_ENTITY, entity.name());
		wp.setParam(Constants.P_MODE, Constants.Mode.NEW.name());
		wpc.addWorkpage(wp);

	}

	public void onCopy(ActionEvent event) {
		// copy object
		IEntityObject newEntity = ENTITYLISTLOGIC.copy(entity, entityObject);
		if(newEntity!=null) {
			// create separate workpage
			IWorkpageDispatcher wpd = getOwningDispatcher();
			IWorkpageContainer wpc = getWorkpageContainer();

			IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
					newEntity.getId(),String.format(Helper.getLiteral("copy_of"), entityObject.toString()), null, true, parentBean, authorization, newEntity );
			wp.setParam(Constants.P_ENTITY, entity.name());
			wp.setParam(Constants.P_MODE, Constants.Mode.COPY.name());
			wpc.addWorkpage(wp);
		}
	}

	private void changeMode(Constants.Mode mode) {
		this.mode = mode;
		if (mode == Constants.Mode.CHANGE ||
			mode == Constants.Mode.SINGLECHANGE) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = false;
			renderSaveButton = true;
			renderCancelButton = true;
			renderCopyButton = false;

		}
		if (mode == Constants.Mode.SHOW) {
			renderNewButton = true;
			renderDeleteButton = true;
			renderEditButton = true;
			renderSaveButton = false;
			renderCancelButton = false;
			renderCopyButton = true;
		}
		if (mode == Constants.Mode.NEW ||
			mode == Constants.Mode.COPY	) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = false;
			renderSaveButton = true;
			renderCancelButton = true;
			renderCopyButton = false;
		}
		if (mode == Constants.Mode.FINAL) {
			renderNewButton = false;
			renderDeleteButton = false;
			renderEditButton = false;
			renderSaveButton = false;
			renderCancelButton = false;
			renderCopyButton = false;
			authorization = authorization.NONE;
		}
	}

	public void setEntityDetailPage(String value) {
		m_entityDetailPage = value;
	}

}
