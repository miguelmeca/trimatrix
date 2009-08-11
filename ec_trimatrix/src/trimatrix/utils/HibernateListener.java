package trimatrix.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateSessionFactory.getSessionFactory().close(); // Free all resources		   
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {		
		HibernateSessionFactory.getSessionFactory(); // Just call the static initializer of that class 		
	}

}
