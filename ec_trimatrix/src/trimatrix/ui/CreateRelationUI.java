package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.OKPopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.entities.IEntityObject;
import trimatrix.logic.RelationListLogic;
import trimatrix.relations.IRelationObject;
import trimatrix.ui.utils.IPopUpCallback;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.CreateRelationUI}")

public class CreateRelationUI extends MyWorkpageDispatchedBean implements Serializable {
	private final RelationListLogic RELATIONLISTLOGIC = getLogic().getRelationListLogic();

	private IEntityObject partner1;
    public String getPartner1() { return partner1.toString(); }

    private IEntityObject partner2;
    public String getPartner2() { return partner2.toString(); }

	public CreateRelationUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		reltypEnabled = true;
	}

	protected Constants.Relation relation;
    public String getReltyp() { return relation.type(); }

    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    public void onSave(ActionEvent event) {
    	OKPopup popup = null;
    	// check mandatory fields
    	if(partner1==null || partner2==null || relation==null){
    		Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
    		return;
    	}
    	// save prepare
    	IRelationObject objRelation = RELATIONLISTLOGIC.create(relation);
    	objRelation.setPartner1(partner1.getId());
    	objRelation.setReltypKey(relation.type());
    	objRelation.setPartner2(partner2.getId());
    	// save
    	try {
    		RELATIONLISTLOGIC.save(relation, objRelation);
    		Statusbar.outputSuccess(Helper.getMessages("save_success"));
    		callback.ok(null);
    	} catch (DataIntegrityViolationException dive) {
    		logger.error("Relation could not be saved (Data Integrity) : " + dive.getRootCause().toString());
    		popup = OKPopup.createInstance(Helper.getLiteral("error"),Helper.getMessages("relation_save_failure"));
    	} catch (Exception ex){
			logger.error("Relation could not be saved : " + ex.toString());
    		popup = OKPopup.createInstance(Helper.getLiteral("error"),Helper.getMessages("relation_save_failure"));
		}
    	if (popup!=null) {
    		popup.getModalPopup().setWidth(300);
    		popup.getModalPopup().setHeight(150);
    		popup.getModalPopup().setLeftTopReferenceCentered();
    	}
    }

    public void onSelectPartner1(ActionEvent event) {
    	final Constants.Entity entity = relation.getPartner1();
    	Constants.Page selectionPage = entity.getSelectionPage();
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(entity);
    	entitySelectionUI.buildData(entity);
       	entitySelectionUI.prepareCallback(new ISelectionCallback(){
   			public void cancel() {
   				m_popup.close();
   			}
   			public void selected(String id) {
   				partner1 = getLogic().getEntityListLogic().get(entity, id);
   				m_popup.close();
   			}});
       	m_popup = getWorkpage().createModalPopupInWorkpageContext();
       	m_popup.setLeftTopReferenceCentered();
       	m_popup.open(selectionPage.getUrl(), selectionPage.getDescription(), 800, 560, this);
    }

    public void onSelectPartner2(ActionEvent event) {
    	final Constants.Entity entity = relation.getPartner2();
    	Constants.Page selectionPage = entity.getSelectionPage();
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(entity);
    	entitySelectionUI.buildData(entity);
       	entitySelectionUI.prepareCallback(new ISelectionCallback(){
   			public void cancel() {
   				m_popup.close();
   			}
   			public void selected(String id) {
   				partner2 = getLogic().getEntityListLogic().get(entity, id);
   				m_popup.close();
   			}});
       	m_popup = getWorkpage().createModalPopupInWorkpageContext();
       	m_popup.setLeftTopReferenceCentered();
       	m_popup.open(selectionPage.getUrl(), selectionPage.getDescription(), 800, 560, this);
    }

    public void setRelationType(Constants.Relation relation) {
    	this.relation = relation;
    	reltypEnabled = false;
    }

    protected ValidValuesBinding reltypeVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.RELTYPS);
    public ValidValuesBinding getReltypeVvb() { return reltypeVvb; }

    protected boolean reltypEnabled;
    public boolean getReltypEnabled() { return reltypEnabled; }

    protected IPopUpCallback callback;
    public void prepareCallback(IPopUpCallback callback) {
    	this.callback = callback;
    }
}
