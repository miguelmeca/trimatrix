package services;

import junit.framework.Assert;

import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.ContextStatic;

public class ValueListBindingServiceTest {
	private static ApplicationContext context = ContextStatic.getInstance();
	private static ServiceLayer serviceLayer = ServiceLayer.getFromApplicationContext(context);	

	@Test
	public void vvbStatic() {		
		ValidValuesBinding vvb = serviceLayer.getValueListBindingService().FUNCTIONS;
		vvb.sortByName();
		Assert.assertEquals(Constants.EXPONENTIAL, vvb.getValidValues().next().getText());
	}

}
