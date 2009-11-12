package trimatrix.ui.utils;

import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.IWorkpageStarter;
import org.eclnt.workplace.WorkpageStartInfo;

/**
 * We have to create an own starter because we also have
 * an own implementation for workpages, e.g. the closing 
 * scenario is handled there
 * @author reich
 *
 */
public class MyWorkpageStarter implements IWorkpageStarter {

	@Override
	public IWorkpage startWorkpage(IWorkpageDispatcher workpageDispatcher,
			IWorkpageContainer workpageContainer, WorkpageStartInfo startInfo) {
		if (startInfo.getOpenMultipleInstances() == false) {
			IWorkpage wp = workpageContainer
					.getWorkpageForId(startInfo.getId());
			if (wp != null) {
				workpageContainer.switchToWorkpage(wp);
				return wp;
			}
		}
		// create new page
		MyWorkpage wp = new MyWorkpage(workpageDispatcher, startInfo.getJspPage(),
				startInfo.getId(), startInfo.getText(), startInfo.getImage(),
				startInfo.isDecorated());
		wp.setPopupSupported(startInfo.isPopupSupported());
		wp.setSelectorTitle(startInfo.getSelectorTitle());
		wp.setIconURL(startInfo.getImage());
		for (String paramName : startInfo.getParamMap().keySet())
			wp.setParam(paramName, startInfo.getParamMap().get(paramName));
		// either add as workpage into the content area or open as popup
		if (startInfo.getOpenAsPopupDyDefault() == false)
			workpageContainer.addWorkpage(wp);
		else
			workpageContainer.addWorkpageAsPopup(wp);
		return wp;
	}
}
