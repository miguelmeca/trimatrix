package trimatrix.ui.utils;

import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.Workpage;

import trimatrix.structures.SAuthorization;

public class MyWorkpage extends Workpage{

	private Object parentBean;
	private SAuthorization authorization;
	
	public MyWorkpage(IWorkpageDispatcher dispatcher, String jspPage,
			String id, String title, String iconURL, boolean isDecorated) {
		super(dispatcher, jspPage, id, title, iconURL, isDecorated);
	}
	
	public MyWorkpage(IWorkpageDispatcher dispatcher, String jspPage,
			String id, String title, String iconURL, boolean isDecorated, 
			Object parentBean, SAuthorization authorization) {
		super(dispatcher, jspPage, id, title, iconURL, isDecorated);
		this.parentBean = parentBean;	
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

	@Override
	public boolean close() {
		return super.close();
	}	
}
