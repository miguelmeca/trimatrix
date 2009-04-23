package trimatrix.ui;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.WorkplaceUI}")

public class WorkplaceUI extends MyWorkpageDispatchedBean implements Serializable
{

	public WorkplaceUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}
	
}
