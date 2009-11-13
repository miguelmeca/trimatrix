package trimatrix.ui.utils;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.Workpage;

import trimatrix.entities.IEntityObject;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.EntityDetailUI;
import trimatrix.utils.Constants.Mode;
import trimatrix.utils.Constants.Page;

public class MyWorkpage extends Workpage{	

	private Object parentBean;
	private IEntityObject entityObject;
	private SAuthorization authorization;
	
	public MyWorkpage(IWorkpageDispatcher dispatcher, String jspPage,
			String id, String title, String iconURL, boolean isDecorated) {
		super(dispatcher, jspPage, id, title, iconURL, isDecorated);
	}
	
	public MyWorkpage(IWorkpageDispatcher dispatcher, String jspPage,
			String id, String title, String iconURL, boolean isDecorated, 
			Object parentBean, SAuthorization authorization, IEntityObject entityObject) {
		super(dispatcher, jspPage, id, title, iconURL, isDecorated);
		this.parentBean = parentBean;	
		this.entityObject = entityObject;
		this.authorization = authorization;
	}		

	public Object getParentBean() {
		return parentBean;
	}

	public void setParentBean(Object parentBean) {
		this.parentBean = parentBean;
	}

	public SAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(SAuthorization authorization) {
		this.authorization = authorization;
	}

	public IEntityObject getEntityObject() {
		return entityObject;
	}

	public void setEntityObject(IEntityObject entityObject) {
		this.entityObject = entityObject;
	}

	@Override
	public boolean close() {		
		if(Page.ENTITYDETAIL.getUrl().equalsIgnoreCase(getJspPage())) {
			EntityDetailUI entityDetailUI = (EntityDetailUI) getDispatcher().getDispatchedBean(EntityDetailUI.class);
			if(entityDetailUI!=null && entityDetailUI.getMode()!=Mode.SHOW) {
				Statusbar.outputAlert("Please save data before closing the workpage!");
				return false;
			}
		}
		return super.close();
		
	}	
	
	@Override
	public void closeForced() {				
		super.closeForced();		
	}
}
