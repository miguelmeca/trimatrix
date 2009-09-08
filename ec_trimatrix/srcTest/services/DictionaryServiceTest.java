package services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import trimatrix.utils.Helper;

public class DictionaryServiceTest {

	@Test
	public void testIsEmailValid() {
		assertTrue(Helper.isEmailValid("reich.markus@gmail.com"));
		assertFalse(Helper.isEmailValid("reich@go"));
		assertFalse(Helper.isEmailValid("reich.markus"));
	}
}
