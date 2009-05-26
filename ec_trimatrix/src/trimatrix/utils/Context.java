package trimatrix.utils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Context {
	public static ApplicationContext getInstance() {
		FacesContext context= FacesContext.getCurrentInstance();
		
	    ServletContext servletContext = (ServletContext)context.getExternalContext().getContext();
	    return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	    
	}
}
