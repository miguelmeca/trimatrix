package services;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.entities.IEntityData;
import trimatrix.relations.IRelationData;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
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
		List<SFunctionTree> functionTree = sqlExecutorService.getFunctionTree(Constants.Role.ADMIN, "de");
		Assert.assertTrue(functionTree.size() > 0);
	}
	
	@Test
	public void testPersonPersonQuery() {		
		List<IRelationData> relations = sqlExecutorService.getPersonPersonRelation(Constants.Relation.PERSONPERSON, "de");
		Assert.assertTrue(relations.size() > 0);
	}
}
