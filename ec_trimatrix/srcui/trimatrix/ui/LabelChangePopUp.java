package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Labels;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass (expressionBase="#{d.LabelChangePopUp}")

public class LabelChangePopUp extends MyWorkpageDispatchedBean implements Serializable
{
    private String label_id;
	
	public LabelChangePopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    public void onChange(ActionEvent event) {
    	if(getLogic().getLabelLogic().changeLabel(label_id, m_description, m_color)) callback.cancel();
    	refreshLabels();
    }

    protected String m_description;
    public String getDescription() { return m_description; }
    public void setDescription(String value) { m_description = value; }

    protected String m_color;
    public String getColor() { return m_color; }
    public void setColor(String value) { m_color = value; }
    
    public boolean setLabel(String label_id) {
    	if(label_id==null || label_id.length()==0) return false;
    	// get label from db
    	Labels label = getLogic().getLabelLogic().getLabel(label_id);
    	if(label==null) return false;
    	this.label_id = label.getId();    	
    	setColor(label.getColor());
    	setDescription(label.getDescription());
    	return true;
    }
    
    public interface IPopupCallback {
    	public void cancel();
    }
    
    protected IPopupCallback callback;

    public void prepareCallback(IPopupCallback callback) {
    	this.callback = callback;
    }
}
