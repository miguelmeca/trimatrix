package trimatrix.services;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailService {
	public static final Log logger = LogFactory.getLog(MailService.class);
	private MailSender mailSender;
    private SimpleMailMessage mailMessage;
	
    
    public void sendTestMail() {
    	SimpleMailMessage message = new SimpleMailMessage(mailMessage);
    	message.setSentDate(new Date());
		message.setText("Blah blah blah...");
		message.setSubject("Test");
		message.setTo("reich.markus@gmail.com");
		mailSender.send(message);
    }
    
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	
	public static MailService getFromApplicationContext(ApplicationContext ctx) {
		return (MailService) ctx.getBean("mailService");
	}
}
