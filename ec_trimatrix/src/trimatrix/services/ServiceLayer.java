package trimatrix.services;

import org.springframework.context.ApplicationContext;

import trimatrix.utils.Dictionary;

public class ServiceLayer {
	private ValueListBindingService valueListBindingService;
	private Dictionary dictionaryService;

	public ValueListBindingService getValueListBindingService() {
		return valueListBindingService;
	}

	public void setValueListBindingService(
			ValueListBindingService valueListBindingService) {
		this.valueListBindingService = valueListBindingService;
	}
		
	public Dictionary getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public static ServiceLayer getFromApplicationContext(ApplicationContext ctx) {
		return (ServiceLayer) ctx.getBean("serviceLayer");
	}
}
