package logic;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.logic.CompetitionLogic;
import trimatrix.logic.LogicLayer;
import trimatrix.logic.helper.Limit;
import trimatrix.utils.ContextStatic;

public class CompetitionLogicTest {
	private ApplicationContext context = ContextStatic.getInstance();
	private CompetitionLogic competitionLogic = (LogicLayer.getFromApplicationContext(context)).getCompetitionLogic();

	@Test
	public void testLimits() {
		String[] arrLimits = {"00:30", "00:40", "01:45"};
		String[] arrLimits2 = {"01:30:00", "01:40:59", "11:45:59"};
		String[] arrSwim = {"Markus", "00:35"};
		String[] arrRun = {"Dany", "01:01"};
		String[] arrBike = {"Würtl", "11:01:01"};
		String[] arrSwim2 = {"Reich", "00:35:17"};
		String[] arrRun2 = {"Bucher", "01:01:00"};
		String[] arrBike2 = {"Würtl", "11:01:01"};
		Limit[] limits = {competitionLogic.createLimit("W20", arrLimits, arrSwim, arrRun, arrBike, true), competitionLogic.createLimit("M30", arrLimits2, arrSwim2, arrRun2, arrBike2, false)};
		List<Limit> lstLimits = Arrays.asList(limits);
		String strLimits = competitionLogic.buildString(lstLimits);
		Assert.assertEquals("[{\"bike\":[\"Würtl\",\"11:01:01\"],\"category\":\"W20\",\"limits\":[\"00:30\",\"00:40\",\"01:45\"],\"run\":[\"Dany\",\"01:01\"],\"swim\":[\"Markus\",\"00:35\"],\"swimsuit\":true},{\"bike\":[\"Würtl\",\"11:01:01\"],\"category\":\"M30\",\"limits\":[\"01:30:00\",\"01:40:59\",\"11:45:59\"],\"run\":[\"Bucher\",\"01:01:00\"],\"swim\":[\"Reich\",\"00:35:17\"],\"swimsuit\":false}]", strLimits);
		List<Limit> limits2 = competitionLogic.getLimits(strLimits);
		Assert.assertEquals(limits.length, limits2.size());
		Assert.assertEquals(limits[0].toString(), limits2.get(0).toString());
		Assert.assertEquals(limits[1].toString(), limits2.get(1).toString());
	}
}
