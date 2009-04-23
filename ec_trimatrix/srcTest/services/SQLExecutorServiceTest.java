package services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntityData;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.ContextStatic;


public class SQLExecutorServiceTest {
	
	private static ApplicationContext context = ContextStatic.getInstance();
	private static SQLExecutorService sqlExecutorService = SQLExecutorService.getFromApplicationContext(context);	

	@Test
	public void testUserEntityListQuery() {		
		List<IEntityData> userEntityData = sqlExecutorService.getUserEntities("de", false, true);
		Assert.assertEquals(2, userEntityData.size());
	}
	
	@Test
	public void testFunctionTreeQuery() {		
		List<SFunctionTree> functionTree = sqlExecutorService.getFunctionTree("admin", "de");
		Assert.assertTrue(functionTree.size() > 0);
	}
}
