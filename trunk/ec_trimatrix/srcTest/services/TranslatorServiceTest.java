package services;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.TranslationService;
import trimatrix.utils.ContextStatic;

public class TranslatorServiceTest {

	private ApplicationContext context = ContextStatic.getInstance();
	
	@Test
	public void testGetDescriptionFromDB() {		
		TranslationService translator = TranslationService.getFromApplicationContext(context);
		String translate = "de";
		String translated = translator.getDescriptionFromDB(translate, TranslationService.LANGUAGES, "de");
		Assert.assertEquals("Deutsch", translated);
	}

}
