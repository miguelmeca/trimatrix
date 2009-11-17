package logic;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.logic.CompetitionLogic;
import trimatrix.logic.LogicLayer;
import trimatrix.utils.ContextStatic;

public class CompetitionLogicTest {
	private ApplicationContext context = ContextStatic.getInstance();
	private CompetitionLogic competitionLogic = (LogicLayer.getFromApplicationContext(context)).getCompetitionLogic();
	
	@Test
	public void testLimits() {
		Double[] arrLimits = {1.2, 3.4};
		String strLimits = competitionLogic.buildString(arrLimits);
		Assert.assertEquals("[1.2,3.4]", strLimits);
		Double[] arrLimits2 = competitionLogic.getLimits(strLimits);
		Assert.assertEquals(arrLimits.length, arrLimits2.length);
		Assert.assertEquals(arrLimits[0], arrLimits2[0]);
		Assert.assertEquals(arrLimits[0], arrLimits2[0]);
	}
}
