package trimatrix.ui;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.managedbean.IDispatcher;

import trimatrix.ui.utils.MyDispatchedBean;
import trimatrix.utils.Context;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.TestUI}")

public class TestUI extends MyDispatchedBean implements Serializable
{
    public TestUI(IDispatcher dispatcher) {
		super(dispatcher);		
		Dictionary dictionary = Dictionary.getFromApplicationContext(Context.getInstance());
		m_test = dictionary.getMyUser().getUserName();
    }
	protected String m_test;
    public String getTest() { return m_test; }
    public void setTest(String value) { m_test = value; }

}
