package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.utils.Helper;
import eu.medsea.mimeutil.MimeType;



public class MimeTypeTest {
	@Test
	public void testMimeType() {
		File testFile = new File("testingdata/tom.jpg");
		byte[] testBytes;
		try {
			testBytes = Helper.getBytesFromFile(testFile);
			ByteArrayInputStream byteInputStream = new ByteArrayInputStream(testBytes);
			Collection<?> mimeTypes = eu.medsea.mimeutil.MimeUtil.getMimeTypes(byteInputStream);			
			MimeType mimeType = (MimeType)mimeTypes.toArray()[0];
			Assert.assertEquals("image/jpeg", mimeType.toString());
		} catch (IOException e) {
			Assert.fail();
		}		
	}	
}
