package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.DoctorEntity;
import trimatrix.entities.IEntityData;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@CCGenClass (expressionBase="#{d.DoctorSelectionUI}")

public class DoctorSelectionUI extends EntitySelectionUI implements Serializable
{
	protected ValidValuesBinding countriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COUNTRY);
    public ValidValuesBinding getCountriesVvb() { return countriesVvb; }
   	
	public DoctorSelectionUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}    

	protected FIXGRIDListBinding<GridListItem<DoctorEntity.Data>> m_gridList = new FIXGRIDListBinding<GridListItem<DoctorEntity.Data>>();
    public FIXGRIDListBinding<GridListItem<DoctorEntity.Data>> getGridList() { return m_gridList; }
    public void setGridList(FIXGRIDListBinding<GridListItem<DoctorEntity.Data>> value) { m_gridList = value; }
    
    public void onSelect(ActionEvent event) {
    	super.onSelect(m_gridList.getSelectedItem());
    }
    
    public void buildData(Entity entity) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity);	
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {	
			DoctorEntity.Data data = (DoctorEntity.Data)datum;
			m_gridList.getItems().add(new GridListItem<DoctorEntity.Data>(data));
		}
	}     
}
