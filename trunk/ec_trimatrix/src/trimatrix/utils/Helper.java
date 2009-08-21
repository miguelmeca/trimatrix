package trimatrix.utils;

import org.eclnt.jsfserver.resources.ResourceManager;

public class Helper {
	public static String getLiteral(String property) {
		return ResourceManager.getRuntimeInstance().readProperty(Constants.LITERALS,property);
	}
}
