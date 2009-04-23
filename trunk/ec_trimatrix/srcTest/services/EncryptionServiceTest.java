package services;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.EncryptionService;
import trimatrix.utils.ContextStatic;

public class EncryptionServiceTest {
	
	private EncryptionService encryptionService;
	private ApplicationContext context = ContextStatic.getInstance();
		
	@Before
	public void setUp() {
		encryptionService = EncryptionService.getFromApplicationContext(context);
	}
	
	@Test
	public void testEncryptSHA() {		
		String plaintext = "TextToEncrypt";
		String SHAText = null;
		try {
			SHAText = encryptionService.encryptSHA(plaintext);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Assert.assertEquals("0sMMWBsGfe0Jb07ol+ySRUAuOGI=", SHAText);
	}
	
	@Test
	public void testEncryptDES() {		
		String salt = "SaltInSoup";
		String plaintext = "TextToEncrypt";
		String DESText = null;
		try {
			DESText = encryptionService.encryptDES(salt, plaintext);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Assert.assertEquals("uaIIVO+/83zbpbNKoLuZ+g==", DESText);
		
		String plaintextDecrypted = null;
		try {
			plaintextDecrypted = encryptionService.decryptDES(salt, DESText);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Assert.assertEquals(plaintext, plaintextDecrypted);
	}

}
