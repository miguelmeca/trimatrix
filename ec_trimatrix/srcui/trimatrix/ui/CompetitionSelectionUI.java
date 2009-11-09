package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.CompetitionEntity;
import trimatrix.entities.IEntityData;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@CCGenClass (expressionBase="#{d.CompetitionSelectionUI}")

public class CompetitionSelectionUI extends EntitySelectionUI implements Serializable
{   	
	public CompetitionSelectionUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		if(entity==null) setEntity(Entity.COMPETITION);			
		buildData(entity);
	}    

	protected FIXGRIDListBinding<GridListItem<CompetitionEntity.Data>> m_gridList = new FIXGRIDListBinding<GridListItem<CompetitionEntity.Data>>();
    public FIXGRIDListBinding<GridListItem<CompetitionEntity.Data>> getGridList() { return m_gridList; }
    public void setGridList(FIXGRIDListBinding<GridListItem<CompetitionEntity.Data>> value) { m_gridList = value; }
    
    public void onSelect(ActionEvent event) {
    	super.onSelect(m_gridList.getSelectedItem());
    }
    
    private void buildData(Constants.Entity entityName) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entityName);	
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {	
			CompetitionEntity.Data data = (CompetitionEntity.Data)datum;
			m_gridList.getItems().add(new GridListItem<CompetitionEntity.Data>(data));
		}
	}     
}
