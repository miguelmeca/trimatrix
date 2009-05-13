package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.CreateRelationUI}")

public class CreateRelationUI extends MyWorkpageDispatchedBean implements Serializable
{
    public CreateRelationUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		reltypEnabled = true;
	}

	protected Constants.Relation relation;
    public String getReltyp() { return relation.type(); }

    public void onCancel(ActionEvent event) {
    	// TODO close pop up
    }

    public void onSave(ActionEvent event) {
    	
    }

    public void onSelectPartner2(ActionEvent event) {
    	
    }

    public void onSelectPartner1(ActionEvent event) {
    	
    }

    public void setRelationType(Constants.Relation relation) {
    	this.relation = relation;
    	reltypEnabled = false;
    }
    
    protected ValidValuesBinding reltypeVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.RELTYPS);
    public ValidValuesBinding getReltypeVvb() { return reltypeVvb; }

    protected boolean reltypEnabled;
    public boolean getReltypEnabled() { return reltypEnabled; }

}
