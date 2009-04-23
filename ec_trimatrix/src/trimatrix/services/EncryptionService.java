package trimatrix.services;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Meex Klasse führt one-way Hashing durch
 */
public class EncryptionService {

	public String encryptSHA(String plaintext) throws Exception {
		MessageDigest md = null;
		md = MessageDigest.getInstance("SHA");
		md.update(plaintext.getBytes("UTF-8"));
		byte raw[] = md.digest();
		String hash = (new BASE64Encoder()).encode(raw);
		return hash;
	}

	public String encryptDES(String salt, String plaintext) throws Exception{
		// Salz anpassen, muss 8stellig sein!
		if (salt.length() > 8) {
			salt = salt.substring(0, 8);
		}
		if (salt.length() < 8) {
			StringUtils.rightPad(salt, 8, '_');
		}
		
		Key key = new SecretKeySpec(salt.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] plaintextUTF8 = plaintext.getBytes("UTF8");		
		byte[] plaintextEnrypted = cipher.doFinal(plaintextUTF8);
		return new BASE64Encoder().encode(plaintextEnrypted);
	}

	public String decryptDES(String salt, String plaintextEncoded) throws Exception {
		// Salz anpassen, muss 8stellig sein!
		if (salt.length() > 8) {
			salt = salt.substring(0, 8);
		}
		if (salt.length() < 8) {
			StringUtils.rightPad(salt, 8, '_');
		}
		
		Key key = new SecretKeySpec(salt.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] plaintextEncodedByte = new BASE64Decoder().decodeBuffer(plaintextEncoded);
		byte[] plaintext = cipher.doFinal(plaintextEncodedByte);
		return new String(plaintext, "UTF8");
	}
	
	public static EncryptionService getFromApplicationContext(ApplicationContext ctx) {
		return (EncryptionService) ctx.getBean("encryptionService");
	}
}
