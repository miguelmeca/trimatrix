package trimatrix.services;

import org.springframework.context.ApplicationContext;

public class ServiceLayer {
	private ValueListBindingService valueListBindingService;

	public ValueListBindingService getValueListBindingService() {
		return valueListBindingService;
	}

	public void setValueListBindingService(
			ValueListBindingService valueListBindingService) {
		this.valueListBindingService = valueListBindingService;
	}
	
	public static ServiceLayer getFromApplicationContext(ApplicationContext ctx) {
		return (ServiceLayer) ctx.getBean("serviceLayer");
	}
}
