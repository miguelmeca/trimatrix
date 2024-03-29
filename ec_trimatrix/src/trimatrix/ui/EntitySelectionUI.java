package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.IEntityData;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Helper;

public abstract class EntitySelectionUI extends MyWorkpageDispatchedBean implements Serializable, IEntitySelectionUI
{
	protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
    protected List<IEntityData> gridData;

	public EntitySelectionUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	/* (non-Javadoc)
	 * @see trimatrix.ui.IEntitySelectionUI#onCancel(javax.faces.event.ActionEvent)
	 */
	public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    /* (non-Javadoc)
	 * @see trimatrix.ui.IEntitySelectionUI#onSelect(trimatrix.ui.EntitySelectionUI.GridListItem)
	 */
    public void onSelect(GridListItem<? extends IEntityData> item) {
    	if (item == null) {
    		Statusbar.outputMessage(Helper.getMessages("no_entry_selected"));
    		return;
    	}
    	//Statusbar.outputMessage("Entity " + item.entity.toString() + " selected!");
    	callback.selected(item.entity.getId());
    }

	public class GridListItem<E extends IEntityData> extends FIXGRIDItem implements java.io.Serializable
    {
    	protected E entity;

    	public GridListItem(E entity) {
			super();
			this.entity = entity;
		}


        public E getEntity() {return entity;}
		public void setEntity(E entity) {this.entity = entity;}

		@Override
		public void onRowExecute() {
			super.onRowExecute();
			//Statusbar.outputMessage("Entity " + entity.toString() + " selected!");
			callback.selected(entity.getId());
		}
    }

    protected ISelectionCallback callback;
    /* (non-Javadoc)
	 * @see trimatrix.ui.IEntitySelectionUI#prepareCallback(trimatrix.ui.EntitySelectionUI.ISelectionCallback)
	 */
    public void prepareCallback(ISelectionCallback callback) {
    	this.callback = callback;
    }
}
