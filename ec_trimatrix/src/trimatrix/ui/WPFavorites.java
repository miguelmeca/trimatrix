package trimatrix.ui;

import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFavorites;

public class WPFavorites extends WorkplaceFavorites {


	public WPFavorites(IDispatcher dispatcher) {		
		super(dispatcher);
		setIconDirectoryPath("/images/workplaceicons/");
	}	

}
