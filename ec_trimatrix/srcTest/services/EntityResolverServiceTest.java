package services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.EntityResolverService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.ContextStatic;

public class EntityResolverServiceTest {

	private ApplicationContext context = ContextStatic.getInstance();
	
	@Test
	public void testGetGridMetaData() {
		EntityResolverService entityResolverService = EntityResolverService.getFromApplicationContext(context);
		// user entity
		List<SGridMetaData> userMetaData = entityResolverService.getGridMetaData(Constants.Entity.USER);
		Assert.assertEquals(6, userMetaData.size());
		// person entity
		List<SGridMetaData> personMetaData = entityResolverService.getGridMetaData(Constants.Entity.PERSON);
		Assert.assertEquals(2, personMetaData.size());
		
	}

}
