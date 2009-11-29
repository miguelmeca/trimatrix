package trimatrix.utils;

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

import trimatrix.structures.SAttachment;

import com.sun.istack.internal.ByteArrayDataSource;

public class MailSender {
	private static final String SMTP_HOST_NAME = Constants.MAIL_BUNDLE
			.getString(Constants.SMTP_HOST_NAME);
	private static final String SMTP_AUTH_USER = Constants.MAIL_BUNDLE
			.getString(Constants.SMTP_AUTH_USER);
	private static final String SMTP_AUTH_PWD = Constants.MAIL_BUNDLE
			.getString(Constants.SMTP_AUTH_PASS);

	/**
	 * Post a smtp message to certain recipients
	 * @param recipients Recipients
	 * @param subject	Subject
	 * @param message	Message
	 * @throws MessagingException
	 */
	public static void postMail(String recipients[], String subject, String message, String type, SAttachment attachments)
			throws MessagingException {
		boolean debug = false;
		// Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", Constants.TRUE);
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", Constants.TRUE);

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);

		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(Constants.EMAIL_FROM_ADDRESS);
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
		messageBodyPart.setContent(message,type);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		// Attachment handling
		if (attachments!=null) {
			messageBodyPart = new MimeBodyPart();		
			DataSource source = new ByteArrayDataSource(attachments.content, attachments.mimeType);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachments.name);
			multipart.addBodyPart(messageBodyPart);
		}
		
		// Put parts in message
		msg.setContent(multipart);
		
		// Send mail
		Transport.send(msg);
	}

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
