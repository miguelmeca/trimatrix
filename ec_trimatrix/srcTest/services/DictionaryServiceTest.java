package services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import trimatrix.utils.Dictionary;

public class DictionaryServiceTest {

	@Test
	public void testIsEmailValid() {
		assertTrue(Dictionary.isEmailValid("reich.markus@gmail.com"));
		assertFalse(Dictionary.isEmailValid("reich@go"));
		assertFalse(Dictionary.isEmailValid("reich.markus"));
	}
}
