package logic;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.logic.CompetitionLogic;
import trimatrix.logic.LogicLayer;
import trimatrix.logic.CompetitionLogic.Limit;
import trimatrix.utils.ContextStatic;

public class CompetitionLogicTest {
	private ApplicationContext context = ContextStatic.getInstance();
	private CompetitionLogic competitionLogic = (LogicLayer.getFromApplicationContext(context)).getCompetitionLogic();
	
	@Test
	public void testLimits() {
		Double[] arrLimits = {1.2, 3.4};
		Double[] arrLimits2 = {2.123, 4d};
		Limit[] limits = {competitionLogic.createLimit("W20", arrLimits), competitionLogic.createLimit("M30", arrLimits2)};
		List<Limit> lstLimits = Arrays.asList(limits);
		String strLimits = competitionLogic.buildString(lstLimits);
		Assert.assertEquals("[{W20:1.2,3.4};{M30:2.123,4.0}]", strLimits);
		Limit[] limits2 = competitionLogic.getLimits(strLimits);
		Assert.assertEquals(limits.length, limits2.length);
		Assert.assertEquals(limits[0].toString(), limits2[0].toString());
		Assert.assertEquals(limits[1].toString(), limits2[1].toString());
	}
}
