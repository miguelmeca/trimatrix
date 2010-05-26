package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.IEntityData;
import trimatrix.entities.UserEntity;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.UserSelectionUI}")

public class UserSelectionUI extends MyWorkpageDispatchedBean implements Serializable
{
    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    public void onSelect(ActionEvent event) {
    	GridListItem item = m_gridList.getSelectedItem();
    	if (item == null) {
    		Statusbar.outputAlert(Helper.getMessages("no_entry_selected"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
    		return;
    	}
    	callback.selected(item.user.getId());
    }

    public UserSelectionUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		buildData();
	}

    private final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
    private List<IEntityData> gridData;

	protected FIXGRIDListBinding<GridListItem> m_gridList = new FIXGRIDListBinding<GridListItem>();
    public FIXGRIDListBinding<GridListItem> getGridList() { return m_gridList; }
    public void setGridList(FIXGRIDListBinding<GridListItem> value) { m_gridList = value; }

    public class GridListItem extends FIXGRIDItem implements java.io.Serializable
    {
    	private UserEntity.Data user;

    	public GridListItem(UserEntity.Data user) {
			super();
			this.user = user;
		}


        public UserEntity.Data getUser() {return user;}
		public void setUser(UserEntity.Data user) {this.user = user;}

		@Override
		public void onRowExecute() {
			super.onRowExecute();
		}
    }

    private void buildData() {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(Constants.Entity.USER, Constants.NO_FILTER);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {
			UserEntity.Data user = (UserEntity.Data)datum;
			m_gridList.getItems().add(new GridListItem(user));
		}
	}

    private ISelectionCallback callback;
    public void prepareCallback(ISelectionCallback callback) {
    	this.callback = callback;
    }


}
