package services;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.ServiceLayer;
import trimatrix.utils.ContextStatic;

public class MailServiceTest {
	private static ApplicationContext context = ContextStatic.getInstance();
	private static ServiceLayer serviceLayer = ServiceLayer.getFromApplicationContext(context);
	
	@Test
	public void testSendTestMail() {
		serviceLayer.getMailService().sendTestMail();
	}

}
