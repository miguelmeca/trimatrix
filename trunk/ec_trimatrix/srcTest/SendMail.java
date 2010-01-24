import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import trimatrix.utils.Constants;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.sun.istack.internal.ByteArrayDataSource;

public class SendMail {
	private static final String SMTP_HOST_NAME = Constants.MAIL_BUNDLE
			.getString(Constants.SMTP_HOST_NAME);
	private static final String SMTP_AUTH_USER = Constants.MAIL_BUNDLE
			.getString(Constants.SMTP_AUTH_USER);
	private static final String SMTP_AUTH_PWD = Constants.MAIL_BUNDLE
			.getString(Constants.SMTP_AUTH_PASS);

	private static final String emailMsgTxt = "Online Order Confirmation Message. Also include the Tracking Number.";
	private static final String emailSubjectTxt = "Order Confirmation Subject";
	private static final String emailFromAddress = Constants.MAIL_BUNDLE
			.getString(Constants.EMAIL_FROM_ADDRESS);

	// Add List of Email address to who email needs to be sent to
	private static final String[] emailList = { "reich.markus@gmail.com" };

	public static void main(String args[]) throws Exception {
//		ApplicationContext context = ContextStatic.getInstance();
//		MailSender mailSender = (MailSender)context.getBean("mailSender");
//		SimpleMailMessage mailMessage = (SimpleMailMessage)context.getBean("mailMessage");
//		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
//		message.setSentDate(new Date());
//		message.setText("Blah blah blah...");
//		message.setSubject("Test");
//		message.setTo("reich.markus@gmail.com");
//		mailSender.send(message);
		
		SendMail smtpMailSender = new SendMail();
		smtpMailSender.postMail(emailList, emailSubjectTxt, emailMsgTxt,
				emailFromAddress);
		System.out.println("Sucessfully Sent mail to All Users");
	}

	public void postMail(String recipients[], String subject, String message,
			String from) throws MessagingException {
		boolean debug = false;

		// Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);

		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);

		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// fill message
		messageBodyPart.setText(message);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();

		//PdfWriter writer = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//DataSource source = new FileDataSource("Test.pdf");
		Document document = new Document();
		try {			
			//writer = PdfWriter.getInstance(document, baos);
			document.open();
			document.add(new Paragraph("Hello World"));
			document.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		DataSource source = new ByteArrayDataSource(baos.toByteArray(),"application/pdf");
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName("MeinPDF.pdf");
		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		msg.setContent(multipart);
		Transport.send(msg);
	}

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
