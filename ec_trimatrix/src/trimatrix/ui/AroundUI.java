package trimatrix.ui;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.managedbean.IDispatcher;

import trimatrix.ui.utils.MyDispatchedBean;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.AroundUI}")

public class AroundUI extends MyDispatchedBean implements Serializable
{
	String language = Locale.getDefault().getLanguage();
	String country = Locale.getDefault().getCountry();

	public AroundUI(IDispatcher dispatcher)
    {
        super(dispatcher);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.language, this.country));
    }

    protected String m_contentPage = "/logon.jsp";
    public String getContentPage() { return m_contentPage; }
    public void setContentPage(String value) { m_contentPage = value;}

}
