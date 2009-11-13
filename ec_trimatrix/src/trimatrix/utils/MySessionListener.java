package trimatrix.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		// User is added in LogonLogic, after successfull logon
	}

	public void sessionDestroyed(HttpSessionEvent event) {		
		UserTracker.deleteUser(event.getSession());
	}

}
