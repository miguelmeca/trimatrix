package trimatrix.ui.utils;

import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.Workpage;

public class MyWorkpage extends Workpage {

	private Object parentBean;
	
	public MyWorkpage(IWorkpageDispatcher dispatcher, String jspPage,
			String id, String title, String iconURL, boolean isDecorated) {
		super(dispatcher, jspPage, id, title, iconURL, isDecorated);
	}
	
	public MyWorkpage(IWorkpageDispatcher dispatcher, String jspPage,
			String id, String title, String iconURL, boolean isDecorated, Object parentBean) {
		super(dispatcher, jspPage, id, title, iconURL, isDecorated);
		this.parentBean = parentBean;
	}

	public Object getParentBean() {
		return parentBean;
	}

	public void setParentBean(Object parentBean) {
		this.parentBean = parentBean;
	}
	
}
