package trimatrix.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextStatic {
	private static final ApplicationContext INSTANCE = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static ApplicationContext getInstance() {
		return INSTANCE;
	}	
}
