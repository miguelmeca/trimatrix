package services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.db.DAOLayer;
import trimatrix.db.Labels;
import trimatrix.db.Schedules;
import trimatrix.entities.IEntityData;
import trimatrix.relations.IRelationData;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.ContextStatic;


public class SQLExecutorServiceTest {

	private static ApplicationContext context = ContextStatic.getInstance();
	private static SQLExecutorService sqlExecutorService = SQLExecutorService.getFromApplicationContext(context);
	private static DAOLayer daoLayer = DAOLayer.getFromApplicationContext(context);

	@Test
	public void testUserEntityListQuery() {
		List<IEntityData> userEntityData = sqlExecutorService.getUserEntities("de", false, false);
		Assert.assertTrue(userEntityData.size() > 0);
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

	@Test
	public void testEntitiesByLabelQuery() {
		List<Labels> labels = daoLayer.getLabelsDAO().findAll();
		if (labels!=null && labels.size()>0) {
			Map<Constants.Entity, List<String>> map = sqlExecutorService.getEntitiesByLabelList(labels.get(0).getId(), false);
			Assert.assertTrue(map.size() > 0);
		}
	}


	@Test
	public void testScheduleListQuery() {
		List<IEntityData> scheduleEntityData = sqlExecutorService.getScheduleEntities("de", null, null, null, null, false, false);
		Assert.assertTrue(scheduleEntityData.size()>0);
	}
	
	@Test
	public void testSchedulesCriteria() throws ParseException {
		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = dfm.parse("2010-01-16 00:00:00");
		Date end = dfm.parse("2010-03-29 06:00:00");
		List<Schedules> schedules = sqlExecutorService.getSchedules("10f52302-2ddb-11de-86ae-00301bb60f17", new Timestamp(start.getTime()), new Timestamp(end.getTime()), false);
		for(Schedules schedule : schedules) {
			System.out.println(schedule.getStart() + " " + schedule.getDescription());
		}
		System.out.println(schedules.size());
		Assert.assertTrue(schedules.size()>0);
	}
}
