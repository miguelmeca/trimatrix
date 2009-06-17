package trimatrix.ui;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.managedbean.IDispatcher;

import trimatrix.ui.utils.MyDispatchedBean;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.AroundUI}")

public class AroundUI extends MyDispatchedBean implements Serializable
{
	
	public AroundUI(IDispatcher dispatcher)
    {
        super(dispatcher);
    }
	
    protected String m_contentPage = "/logon.jsp";
    public String getContentPage() { return m_contentPage; }
    public void setContentPage(String value) { m_contentPage = value;}

}
