package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.AttachmentEntity;
import trimatrix.entities.IEntityData;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@CCGenClass (expressionBase="#{d.AttachmentSelectionUI}")

public class AttachmentSelectionUI extends EntitySelectionUI implements Serializable
{
	protected ValidValuesBinding categoriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.CATEGORY);
    public ValidValuesBinding getCategoriesVvb() { return categoriesVvb; }
   	
	public AttachmentSelectionUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		buildData(Entity.ATTACHMENT);
	}    

	protected FIXGRIDListBinding<GridListItem<AttachmentEntity.Data>> m_gridList = new FIXGRIDListBinding<GridListItem<AttachmentEntity.Data>>();
    public FIXGRIDListBinding<GridListItem<AttachmentEntity.Data>> getGridList() { return m_gridList; }
    public void setGridList(FIXGRIDListBinding<GridListItem<AttachmentEntity.Data>> value) { m_gridList = value; }
    
    public void onSelect(ActionEvent event) {
    	super.onSelect(m_gridList.getSelectedItem());
    }
    
    public void buildData(Entity entity) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity);	
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {	
			AttachmentEntity.Data data = (AttachmentEntity.Data)datum;
			m_gridList.getItems().add(new GridListItem<AttachmentEntity.Data>(data));
		}
	}     
}
