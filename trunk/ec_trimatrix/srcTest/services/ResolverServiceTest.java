package services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.services.ResolverService;
import trimatrix.structures.SGridMetaData;
import trimatrix.utils.Constants;
import trimatrix.utils.ContextStatic;

public class ResolverServiceTest {

	private ApplicationContext context = ContextStatic.getInstance();
	
	@Test
	public void testGetGridMetaData() {
		ResolverService resolverService = ResolverService.getFromApplicationContext(context);
		// user entity
		List<SGridMetaData> userMetaData = resolverService.getGridMetaData(Constants.Entity.USER, Constants.NO_FILTER);
		Assert.assertTrue(userMetaData.size()>0);
		// person entity
		List<SGridMetaData> personMetaData = resolverService.getGridMetaData(Constants.Entity.PERSON, Constants.NO_FILTER);
		Assert.assertTrue(personMetaData.size()>0);
		
	}

}
