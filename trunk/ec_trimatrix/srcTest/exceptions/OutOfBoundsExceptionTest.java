package exceptions;

import org.junit.Assert;
import org.junit.Test;

import trimatrix.exceptions.OutOfBoundsException;

public class OutOfBoundsExceptionTest {
	@Test
	public void testException() {
		OutOfBoundsException oobe = new OutOfBoundsException(1.5,3.0,5.1);
		Assert.assertEquals("Value 5.1 is not in range [1.5,3.0]", oobe.getMessage());
		Assert.assertEquals("Value 5.1 is not in range [1.5,3.0]", oobe.toString());
	}
}
