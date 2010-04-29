package trimatrix.ui.utils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.eclnt.jsfserver.util.useraccess.IUserAccess;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import trimatrix.services.ServiceLayer;

/**
 * Access user by class UserAccessMgr
 * System.out.println("User: " + UserAccessMgr.getCurrentUser());
 * @author reich
 *
 */
public class MyUserAccess implements IUserAccess{

	@Override
	public String getCurrentUser(FacesContext ctx) {
		ServletContext servletContext = (ServletContext)ctx.getExternalContext().getContext();
	    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	    return ServiceLayer.getFromApplicationContext(context).getDictionaryService().getMyUser().getId();
	}

}
