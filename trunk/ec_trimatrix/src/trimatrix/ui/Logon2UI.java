package trimatrix.ui;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.util.HttpSessionAccess;

@CCGenClass (expressionBase="#{d.Logon2UI}")

public class Logon2UI implements Serializable
{
	public void onReload(ActionEvent event) {
    	HttpSessionAccess.reloadClient();
    }

    public void onEnglish(ActionEvent event) {
    	FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en","US"));
    	FacesContext.getCurrentInstance().renderResponse();

    }

    public void onGerman(ActionEvent event) {
    	FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("de","DE"));
    	FacesContext.getCurrentInstance().renderResponse();
    }

    public void onChange(ActionEvent event) {
//    	FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(m_language));
    }

    public String getClientLanguage()
    {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
    }

    public String getClientCountry()
    {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getCountry();
    }

}
