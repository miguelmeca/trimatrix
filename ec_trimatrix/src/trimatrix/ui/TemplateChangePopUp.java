package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.ImportTemplates;
import trimatrix.ui.utils.IPopUpCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

import static trimatrix.utils.Constants.*;

@CCGenClass (expressionBase="#{d.TemplateChangePopUp}")

public class TemplateChangePopUp extends MyWorkpageDispatchedBean implements Serializable {

	private Entity entity;
	private ImportTemplates template;

	private IPopUpCallback callback;
	public void prepareCallback(IPopUpCallback callback, Entity entity, ImportTemplates template) {
    	this.callback = callback;
    	this.entity = entity;
    	// if template is null, create a new template
    	if(template!=null) {
    		this.template = template;
    	} else {

    	}

    }

	public TemplateChangePopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// TODO Auto-generated constructor stub
	}

	public void onSave(ActionEvent ae) {

	}

	public void onCancel(ActionEvent ae) {

	}
}
