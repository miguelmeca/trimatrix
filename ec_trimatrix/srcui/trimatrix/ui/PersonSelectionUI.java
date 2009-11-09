package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@CCGenClass (expressionBase="#{d.PersonSelectionUI}")

public class PersonSelectionUI extends EntitySelectionUI implements Serializable
{
	protected ValidValuesBinding salutationsVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.SALUTATION);
    public ValidValuesBinding getSalutationsVvb() { return salutationsVvb; }
   	
	public PersonSelectionUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		if(entity==null) setEntity(Entity.PERSON);			
		buildData(entity);
	}    

	protected FIXGRIDListBinding<GridListItem<PersonEntity.Data>> m_gridList = new FIXGRIDListBinding<GridListItem<PersonEntity.Data>>();
    public FIXGRIDListBinding<GridListItem<PersonEntity.Data>> getGridList() { return m_gridList; }
    public void setGridList(FIXGRIDListBinding<GridListItem<PersonEntity.Data>> value) { m_gridList = value; }
    
    public void onSelect(ActionEvent event) {
    	super.onSelect(m_gridList.getSelectedItem());
    }
    
    private void buildData(Constants.Entity entityName) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entityName);	
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {	
			PersonEntity.Data data = (PersonEntity.Data)datum;
			m_gridList.getItems().add(new GridListItem<PersonEntity.Data>(data));
		}
	}     
}
