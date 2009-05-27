package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.OKPopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.springframework.dao.DataIntegrityViolationException;

import trimatrix.entities.IEntityObject;
import trimatrix.logic.RelationListLogic;
import trimatrix.relations.IRelationObject;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@CCGenClass (expressionBase="#{d.CreateRelationUI}")

public class CreateRelationUI extends MyWorkpageDispatchedBean implements Serializable
{
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
    		Statusbar.outputAlert("Not all mandatory fields filled!");
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
    		Statusbar.outputSuccess("Relation saved");
    		callback.ok();
    	} catch (DataIntegrityViolationException dive) {
    		Dictionary.logger.error("Relation could not be saved (Data Integrity) : " + dive.getRootCause().toString());
    		popup = OKPopup.createInstance("Error","Relation could not be saved (Data Integrity)");
    	} catch (Exception ex){			
			Dictionary.logger.error("Relation could not be saved : " + ex.toString());
    		popup = OKPopup.createInstance("Error","Relation could not be saved");    	
		} 	
    	if (popup!=null) {
    		popup.getModalPopup().setWidth(300);
    		popup.getModalPopup().setHeight(150);
    	}
    }

    public void onSelectPartner1(ActionEvent event) {
    	final Constants.Entity entity = relation.getParnter1();
    	Constants.Page selectionPage = entity.getSelectionPage();    	
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(entity);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
   			public void cancel() {
   				m_popup.close();				
   			}
   			public void idSelected(String id) {  
   				partner1 = getLogic().getEntityListLogic().get(entity, id);    				
   				m_popup.close();
   			}});    	
       	String width = HttpSessionAccess.getCurrentRequest().getHeader("eclnt-width");
       	String height = HttpSessionAccess.getCurrentRequest().getHeader("eclnt-height");
       	Dictionary.logger.warn("Width: " + width + " - Height: " + height);
       	m_popup = getWorkpage().createModalPopupInWorkpageContext();           	
       	m_popup.open(selectionPage.getUrl(), selectionPage.getDescription(), 800, 600, this);
    }
    
    public void onSelectPartner2(ActionEvent event) {
    	final Constants.Entity entity = relation.getParnter2();
    	Constants.Page selectionPage = entity.getSelectionPage();    	
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(entity);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
   			public void cancel() {
   				m_popup.close();				
   			}
   			public void idSelected(String id) {  
   				partner2 = getLogic().getEntityListLogic().get(entity, id);    				
   				m_popup.close();
   			}});    	
       	String width = HttpSessionAccess.getCurrentRequest().getHeader("eclnt-width");
       	String height = HttpSessionAccess.getCurrentRequest().getHeader("eclnt-height");
       	Dictionary.logger.warn("Width: " + width + " - Height: " + height);
       	m_popup = getWorkpage().createModalPopupInWorkpageContext();           	
       	m_popup.open(selectionPage.getUrl(), selectionPage.getDescription(), 800, 600, this);
    }   

    public void setRelationType(Constants.Relation relation) {
    	this.relation = relation;
    	reltypEnabled = false;
    }
    
    protected ValidValuesBinding reltypeVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.RELTYPS);
    public ValidValuesBinding getReltypeVvb() { return reltypeVvb; }

    protected boolean reltypEnabled;
    public boolean getReltypEnabled() { return reltypEnabled; }

    public interface ISelectionCallback {
    	public void cancel();
    	public void ok();
    }
    
    protected ISelectionCallback callback;
    public void prepareCallback(ISelectionCallback callback) {
    	this.callback = callback;
    }
}
