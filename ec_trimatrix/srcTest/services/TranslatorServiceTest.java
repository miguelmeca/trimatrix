package services;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.TranslationService;
import trimatrix.utils.ContextStatic;

public class TranslatorServiceTest {	

	private static ApplicationContext context = ContextStatic.getInstance();
	private static TranslationService translationService = TranslationService.getFromApplicationContext(context);

	
	@Test
	public void testDescription() {		
		String translate = "run";
		String translated = translationService.getDescription(translate, "de", TranslationService.TYPE.SCHEDULETYPES);
		Assert.assertEquals("Laufen", translated);
	}

}
