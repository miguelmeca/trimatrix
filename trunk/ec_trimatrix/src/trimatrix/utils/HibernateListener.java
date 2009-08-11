package trimatrix.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateSessionFactory.getSessionFactory().close(); // Free all resources		   
	}

	public void contextInitialized(ServletContextEvent arg0) {		
		HibernateSessionFactory.getSessionFactory(); // Just call the static initializer of that class 		
	}

}
