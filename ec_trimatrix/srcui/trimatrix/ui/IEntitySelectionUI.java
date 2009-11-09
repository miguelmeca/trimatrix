package trimatrix.ui;

import javax.faces.event.ActionEvent;

import trimatrix.entities.IEntityData;
import trimatrix.ui.EntitySelectionUI.GridListItem;
import trimatrix.ui.EntitySelectionUI.ISelectionCallback;
import trimatrix.utils.Constants;

public interface IEntitySelectionUI {

	public abstract void onCancel(ActionEvent event);

	public abstract void onSelect(GridListItem<? extends IEntityData> item);

	public abstract void prepareCallback(ISelectionCallback callback);
	
	public abstract void setEntity(Constants.Entity entity);

}