package trimatrix.ui.utils;

import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.Workpage;

import trimatrix.entities.IEntityObject;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.EntityDetailUI;
import trimatrix.ui.ZonesDetailUI;
import trimatrix.utils.Helper;
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
		// I don't understand why, but the WorkpageSelectorTitle has to be set explicitly?
		setSelectorTitle(title);
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
		boolean doClose = true;
		if(Page.ENTITYDETAIL.getUrl().equalsIgnoreCase(getJspPage())) {
			EntityDetailUI entityDetailUI = (EntityDetailUI) getDispatcher().getDispatchedBean(EntityDetailUI.class);
			if(entityDetailUI!=null && entityDetailUI.getMode()!=Mode.SHOW && entityDetailUI.getMode()!=Mode.FINAL) {
				doClose = false;
			}
		} else if(Page.ZONESDETAIL.getUrl().equalsIgnoreCase(getJspPage())) {
			ZonesDetailUI zonesDetailUI = (ZonesDetailUI) getDispatcher().getDispatchedBean(ZonesDetailUI.class);
			if(zonesDetailUI!=null && zonesDetailUI.getMode()!=Mode.SHOW && zonesDetailUI.getMode()!=Mode.FINAL) {
				doClose =  false;
			}
		}

		if(!doClose) {
			Statusbar.outputAlert(Helper.getMessages("save_data"), Helper.getLiteral("info")).setLeftTopReferenceCentered();
			return false;
		} else return super.close();

	}

	@Override
	public void closeForced() {
		super.closeForced();
	}

	@Override
	public void reactOnDisplay() {
		super.reactOnDisplay();
	}
}
