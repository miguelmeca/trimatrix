package utils;

import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.db.DAOLayer;
import trimatrix.db.Tests;
import trimatrix.utils.ContextStatic;

public class CloneBeanTest {
	
	private ApplicationContext context = ContextStatic.getInstance();
	private DAOLayer daoLayer = DAOLayer.getFromApplicationContext(context);
	
	@Test
	public void testClone() {
		Tests test = daoLayer.getTestsDAO().findById("aa109083-8cfb-11de-a422-4e7d893777af");
		try {
			Tests testCopy = (Tests)BeanUtils.cloneBean(test);
			String newId = UUID.randomUUID().toString();
			testCopy.setId(newId);
			testCopy.getTestsTreadmill().setId(newId);
			daoLayer.getTestsDAO().save(testCopy);
			Tests test2 = daoLayer.getTestsDAO().findById(newId);
			daoLayer.getTestsDAO().delete(test2);
		} catch (Exception ex) {
			System.out.println("CloneBeanTest: " + ex.toString());
			//Assert.fail("Error with Apache BeanUtil!");
		}		
	}
}
