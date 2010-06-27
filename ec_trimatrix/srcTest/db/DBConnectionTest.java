package db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.record.SelectionRecord;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.CompetitionsScoutsId;
import trimatrix.db.DAOLayer;
import trimatrix.db.DayInfos;
import trimatrix.db.DayInfosId;
import trimatrix.db.EntitiesHaveLabels;
import trimatrix.db.EntitiesHaveLabelsId;
import trimatrix.db.IEntityDAO;
import trimatrix.db.ImportTemplates;
import trimatrix.db.ImportTemplatesId;
import trimatrix.db.KRoles;
import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.PersonsHaveCompetitions;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.db.RolesHaveFunctionnodes;
import trimatrix.db.Schedules;
import trimatrix.db.SchedulesDetail;
import trimatrix.db.SchedulesDetailId;
import trimatrix.db.Tests;
import trimatrix.db.TestsErgo;
import trimatrix.db.TestsProtocol;
import trimatrix.db.TestsSwim;
import trimatrix.db.TestsSwimProtocol;
import trimatrix.db.TestsSwimProtocolId;
import trimatrix.db.TestsTreadmill;
import trimatrix.db.Users;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.db.ZonesSwim;
import trimatrix.db.ZonesSwimId;
import trimatrix.entities.IEntityObject;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.structures.SRange;
import trimatrix.utils.Constants;
import trimatrix.utils.ContextStatic;
import trimatrix.utils.HibernateSessionFactory;
import trimatrix.utils.SearchRange;

public class DBConnectionTest {
	private ApplicationContext context = ContextStatic.getInstance();
	private DAOLayer daoLayer = DAOLayer.getFromApplicationContext(context);

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testUsers() {
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		/* 1. Create instance of PersistenceLayer */
		IEntityDAO<Users> usersDAO = daoLayer.getUsersDAO();
		IEntityDAO<Persons> personsDAO = daoLayer.getPersonsDAO();

		/* 2. Create Test User */
		String id = UUID.randomUUID().toString();
		Users newUser = new Users();
		newUser.setId(id);
		newUser.setUserName("Meex");
		newUser.setCreatedAt(now);
		usersDAO.merge(newUser);

		// 2.0.1. Test load functionality
		newUser.setUserName("Meex_");
		Assert.assertEquals("Meex_", newUser.getUserName());
		usersDAO.reload(newUser);
		Assert.assertEquals("Meex", newUser.getUserName());

		/* 2.1 Create a second Test User with same user_name */
		Users newUser2 = new Users();
		newUser2.setId(UUID.randomUUID().toString());
		newUser2.setUserName("Meex");
		now = new java.sql.Timestamp((new java.util.Date()).getTime());
		newUser2.setCreatedAt(now);
		boolean DIVExceptionRaised = false;
		try {
			usersDAO.merge(newUser2);
		} catch (DataIntegrityViolationException dive) {
			DIVExceptionRaised = true;
		}
		Assert.assertTrue(DIVExceptionRaised);

		/* 2.2 Create Test Person and set relation to user */
		Persons newPerson = new Persons();
		id = UUID.randomUUID().toString();
		newPerson.setId(id);
		newPerson.setNameLast("XYZ");
		newPerson.setCreatedAt(now);
		personsDAO.save(newPerson);

		newUser.setUserHash("test");
		newUser.setPerson(newPerson);
		usersDAO.merge(newUser);

		/* 3. Select Data */
		List<Users> users = usersDAO.findAll();
		for (Users user : users) {
			Set<KRoles> roles = user.getRoles();
			if (!Hibernate.isInitialized(roles)) {

			}
			for (KRoles role : roles) {
				Set<RolesHaveFunctionnodes> nodes = role.getFunctionnodes();
				System.out.println("Funktionsknoten : " + nodes.size());
			}
			System.out.println(user.getId() + " : " + user.getUserName()
					+ " : Anzahl Roles = " + roles.size());
			if (user.getUserName().equals("Meex")) {
				Assert.assertEquals("XYZ", user.getPerson().getNameLast());
				usersDAO.delete(user);
			}
		}
		/* 4. Delete Data */
		personsDAO.delete(newPerson);
	}

	@Test
	public void testPersons() throws Exception {
		/* 1. Create instance of PersistenceLayer */
		IEntityDAO<Persons> personsDAO = daoLayer.getPersonsDAO();

		/* 2. Create Test Person */
		Persons newPerson = new Persons();
		String id = UUID.randomUUID().toString();
		newPerson.setId(id);
		BufferedImage img = null;
		img = ImageIO.read(new File("testingdata/tom.jpg"));
		newPerson.setPicture(loadImageFromMemory(img));
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		newPerson.setCreatedAt(now);
		newPerson.setModifiedAt(now);
		personsDAO.merge(newPerson);

		/* 3. Load Data */
		Persons loadedPerson = personsDAO.findById(id);
		Assert.assertEquals(loadedPerson.getPicture(), loadedPerson
				.getPicture());

		/* 4. Delete Data */
		personsDAO.delete(loadedPerson);
	}

	@Test
	public void testSQLStatements() throws Exception {
		SQLExecutorService sqlExecutorService = SQLExecutorService
				.getFromApplicationContext(context);
		List<SFunctionTree> functionTree = sqlExecutorService.getFunctionTree(
				Constants.Role.ADMIN, "en");
		System.out.println(functionTree.size());
	}

	public void testProgramaticTransactionMngmt() throws Exception {
		/* With Template Pattern */
		// single TransactionTemplate shared amongst all methods in this
		// instance
		final TransactionTemplate transactionTemplate = new TransactionTemplate(
				(HibernateTransactionManager) context
						.getBean("transactionManager"));
		// do Operations
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					Timestamp now = new java.sql.Timestamp(
							(new java.util.Date()).getTime());
					/* 1. Create instance of PersistenceLayer */
					IEntityDAO<Users> usersDAO = daoLayer.getUsersDAO();

					/* 2. Create Test User */
					Users newUser = new Users();
					newUser.setId(UUID.randomUUID().toString());
					newUser.setUserName("Meex");
					newUser.setCreatedAt(now);
					newUser.setModifiedAt(now);
					usersDAO.merge(newUser);
					usersDAO.delete(newUser);

					/* 3. Select Data without Lazy Loading possible */
					List<Users> users = usersDAO.findAll();
					for (Users user : users) {
						Set<KRoles> roles = user.getRoles();
						for (KRoles role : roles) {
							Set<RolesHaveFunctionnodes> nodes = role
									.getFunctionnodes();
							System.out.println("Funktionsknoten : "
									+ nodes.size());
						}
						System.out.println(user.getId() + " : "
								+ user.getUserName() + " : Anzahl Roles = "
								+ roles.size());
					}
				} catch (Exception ex) {
					status.setRollbackOnly();
				}
			}
		});
		/* With PlatFormManager */
		HibernateTransactionManager txManager = (HibernateTransactionManager) context.getBean("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be done programmatically
		def.setName("myTx");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = txManager.getTransaction(def);
		try {
			Timestamp now = new java.sql.Timestamp(
					(new java.util.Date()).getTime());
			/* 1. Create instance of PersistenceLayer */
			IEntityDAO<Users> usersDAO = daoLayer.getUsersDAO();

			/* 2. Create Test User */
			Users newUser = new Users();
			newUser.setId(UUID.randomUUID().toString());
			newUser.setUserName("Meex2");
			newUser.setCreatedAt(now);
			newUser.setModifiedAt(now);
			usersDAO.merge(newUser);
			usersDAO.delete(newUser);
		} catch (Exception ex) {
			txManager.rollback(status);
			throw ex;
		}
		txManager.commit(status);
	}

	@Test
	public void testPersonPersonRelations() {
		// create relation
		PersonsHaveRelations relation = new PersonsHaveRelations();
		String id = UUID.randomUUID().toString();
		String partner1 = "0";
		String partner2 = "1";
		relation.setId(id);
		relation.setPartner1(partner1);
		relation.setReltypKey(Constants.Relation.COACH.type());
		relation.setPartner2(partner2);
		//relation.setDefault_(false);
		daoLayer.getPersonsHaveRelationsDAO().merge(relation);

		PersonsHaveRelations relation2 = daoLayer.getPersonsHaveRelationsDAO().findById(id);
		Assert.assertNotNull(relation2);
		daoLayer.getPersonsHaveRelationsDAO().delete(relation2);
	}

	@Test
	public void testEntitiesHaveLabels() {
		// create relation
		EntitiesHaveLabels relation = new EntitiesHaveLabels();
		EntitiesHaveLabelsId id = new EntitiesHaveLabelsId("test","label","person");
		relation.setId(id);
		daoLayer.getEntitiesHaveLabelsDAO().save(relation);

		// get relation
		List<EntitiesHaveLabels> relations = daoLayer.getEntitiesHaveLabelsDAO().findByEntity("test");
		Assert.assertEquals(1, relations.size());
		daoLayer.getEntitiesHaveLabelsDAO().delete(relation);
	}

	@Test
	public void testPersonsAthlete() {
		// Athlete profile
		Persons person = daoLayer.getPersonsDAO().findById("0b0b7658-2ddb-11de-86ae-00301bb60f17");
		PersonsAthlete athlete = person.getProfileAthlete();
		daoLayer.getPersonsDAO().merge(person);
		System.out.println(athlete.getHeight());
	}

	@Test
	public void testTests() {
		String id = UUID.randomUUID().toString();
		Tests test = new Tests(id);
		// ergo
		TestsErgo ergo = new TestsErgo(id);
		ergo.setCadenceLow(90);
		ergo.setCadenceHigh(95);
		test.setTestsErgo(ergo);
		// treadmill
		TestsTreadmill treadmill = new TestsTreadmill(id);
		treadmill.setSpeedVariable(true);
		treadmill.setStepTime("00:01");
		test.setTestsTreadmill(treadmill);
		// swim
		TestsSwim swim = new TestsSwim(id);
		TestsSwimProtocol swimProt = new TestsSwimProtocol(new TestsSwimProtocolId(id,1,1));
		swimProt.setIntensity(100);
		List<TestsSwimProtocol> setSwimProt = new ArrayList<TestsSwimProtocol>();
		setSwimProt.add(swimProt);
		swim.setSteps(setSwimProt);
		test.setTestsSwim(swim);
		// protocoll
		TestsProtocol protocol = new TestsProtocol(id);
		protocol.setModel("Testmodell");
		protocol.setPerformanceMax("02:22");
		test.setTestsProtocol(protocol);
		// test
		test.setDescription("Das ist ein Test für einen Text!");
		test.setType("TREADMILL");
		daoLayer.getTestsDAO().save(test);
		Tests test2 = daoLayer.getTestsDAO().findById(id);
		Assert.assertEquals("TREADMILL", test2.getType());
		Assert.assertNotNull(test2.getTestsErgo());
		Assert.assertNotNull(test2.getTestsTreadmill());
		Assert.assertNotNull(test2.getTestsSwim());
		Assert.assertNotNull(test2.getTestsProtocol());
		Assert.assertEquals("02:22", test2.getTestsProtocol().getPerformanceMax());
		Assert.assertEquals(1, test2.getTestsSwim().getSteps().size());
		Assert.assertEquals(new Integer(100), test2.getTestsSwim().getSteps().iterator().next().getIntensity());
		daoLayer.getTestsDAO().delete(test2);
	}

	@Test
	public void testZones() {
		// Zones Definition
		String id = UUID.randomUUID().toString();
		ZonesDefinition definition = new ZonesDefinition();
		definition.setId(id);
		definition.setCoachId("123456ABCDEFG");
		definition.setSequence(1);
		definition.setLactateLow(10d);
		definition.setLactateHigh(12.1d);
		definition.setDescription("Test");
		daoLayer.getZonesDefinitionDAO().save(definition);
		ZonesDefinition definition2 = daoLayer.getZonesDefinitionDAO().findById(id);
		Assert.assertEquals("123456ABCDEFG", definition2.getCoachId());
		Assert.assertEquals(new Integer(1), definition2.getSequence());
		Assert.assertEquals(new Double(12.1), definition2.getLactateHigh());
		Assert.assertEquals("Test", definition2.getDescription());
		// Zones
		String id2 = UUID.randomUUID().toString();
		Zones zones = new Zones();
		zones.setId(id2);
		zones.setAthleteId("123456ABCDEFG");
		zones.setZonesDefinitionId(id);
		daoLayer.getZonesDAO().save(zones);
		Zones zones2 = daoLayer.getZonesDAO().findById(id2);
		Assert.assertEquals("123456ABCDEFG", zones2.getAthleteId());
		// swim zones
		ZonesSwimId zonesSwimId = new ZonesSwimId(100, zones.getAthleteId(), zones.getZonesDefinitionId());
		ZonesSwim zonesSwim = new ZonesSwim(zonesSwimId);
		zonesSwim.setTimeLow("00:25,50");
		zonesSwim.setTimeHigh("01:25");
		daoLayer.getZonesSwimDAO().save(zonesSwim);
		ZonesSwim zonesSwim2 = daoLayer.getZonesSwimDAO().findById(zonesSwimId);
		Assert.assertEquals("00:25,50", zonesSwim2.getTimeLow());
		Assert.assertEquals("01:25", zonesSwim2.getTimeHigh());
		// delete
		daoLayer.getZonesSwimDAO().delete(zonesSwim);
		daoLayer.getZonesDAO().delete(zones);
		daoLayer.getZonesDefinitionDAO().delete(definition2);
	}

	@Test
	public void testCompetitions() {
		// Competitions
		String id = UUID.randomUUID().toString();
		Date date = new Date();
		Competitions comp = new Competitions(id);
		comp.setDescription("Test Wettkampf");
		comp.setDate(date);
		daoLayer.getCompetitionsDAO().save(comp);
		Competitions comp2 = daoLayer.getCompetitionsDAO().findById(id);
		Assert.assertEquals(comp.getDescription(), comp2.getDescription());
		Assert.assertNotNull(comp2.getDate());
		// Competitions Scout
		CompetitionsScoutsId csId = new CompetitionsScoutsId(id,"ABCDEFGH");
		CompetitionsScouts compScout = new CompetitionsScouts(csId);
		compScout.setLimits("Faktoren");
		daoLayer.getCompetitionsScoutsDAO().save(compScout);
		CompetitionsScouts compScout2 = daoLayer.getCompetitionsScoutsDAO().findById(csId);
		Assert.assertEquals("Faktoren", compScout2.getLimits());
		// Competitions Relation
		String phcId = UUID.randomUUID().toString();
		PersonsHaveCompetitions phc = new PersonsHaveCompetitions(phcId);
		phc.setPartner1("ABC");
		phc.setPartner2(id);
		phc.setReltypKey(Constants.Relation.COMPETITION.type());
		phc.setStandard(true);
		daoLayer.getPersonsHaveCompetitionsDAO().save(phc);
		PersonsHaveCompetitions phc2 = daoLayer.getPersonsHaveCompetitionsDAO().findById(phcId);
		Assert.assertEquals("ABC", phc2.getPartner1());
		Assert.assertTrue(phc2.getStandard());
		daoLayer.getPersonsHaveCompetitionsDAO().delete(phc2);
		daoLayer.getCompetitionsScoutsDAO().delete(compScout2);
		daoLayer.getCompetitionsDAO().delete(comp2);
	}

	@Test
	public void testSchedules() {
		// Competitions
		String id = UUID.randomUUID().toString();
		Schedules schedules = new Schedules(id);
		schedules.setDescription("Test");
		schedules.setColor("#000000");
		schedules.setTemplate(true);
		// test details
		SchedulesDetail schedulesDetail1 = new SchedulesDetail(new SchedulesDetailId(id,1));
		schedulesDetail1.setComment("Test detail 1");
		SchedulesDetail schedulesDetail2 = new SchedulesDetail(new SchedulesDetailId(id,2));
		schedulesDetail2.setComment("Test detail 2");
		List<SchedulesDetail> schedulesDetails = new ArrayList<SchedulesDetail>(2);
		// wrong order to check, order by clause in definition
		schedulesDetails.add(schedulesDetail2);
		schedulesDetails.add(schedulesDetail1);
		schedules.setSchedulesDetail(schedulesDetails);
		daoLayer.getSchedulesDAO().save(schedules);
		Schedules schedules2 = daoLayer.getSchedulesDAO().findById(id);
		Assert.assertEquals(2, schedules2.getSchedulesDetail().size());
		Assert.assertEquals("Test detail 1", schedules2.getSchedulesDetail().get(0).getComment());
		Assert.assertEquals("Test detail 2", schedules2.getSchedulesDetail().get(1).getComment());
		Assert.assertEquals("Test", schedules2.getDescription());
		Assert.assertEquals("#000000", schedules2.getColor());
		Assert.assertEquals(true, schedules2.getTemplate());
		daoLayer.getSchedulesDAO().delete(schedules2);
	}

	@Test
	public void testDayInfos() {
		// Competitions
		Date date = new Date();
		DayInfosId dayInfosId = new DayInfosId(date, "1");
		DayInfos dayInfos = new DayInfos(dayInfosId);
		dayInfos.setRestingHr(42);
		daoLayer.getDayInfosDAO().save(dayInfos);
		DayInfos dayInfos2 = daoLayer.getDayInfosDAO().findById(dayInfosId);
		Assert.assertEquals(new Integer(42), dayInfos2.getRestingHr());
		daoLayer.getDayInfosDAO().delete(dayInfos2);
	}

	@Test
	public void testImports() {
		// Import Results
		ImportTemplatesId id = new ImportTemplatesId("entity", "1234", "test");
		ImportTemplates template = new ImportTemplates(id);
		template.setStartingRow(10);
		template.setMapping("mapping");
		daoLayer.getImportTemplatesDAO().save(template);
		ImportTemplates template2 = daoLayer.getImportTemplatesDAO().findById(id);
		Assert.assertEquals(new Integer(10), template2.getStartingRow());
		Assert.assertEquals("mapping", template2.getMapping());
		daoLayer.getImportTemplatesDAO().delete(template2);
	}

	@Test
	public void testCriterias() {
		Session session = HibernateSessionFactory.getSession();
		SearchRange searchRange = new SearchRange();
		searchRange.add(new SRange<String>("nameFirst", SRange.Operator.CT, "Dani%"));
		searchRange.add(new SRange<String>("nameFirst", SRange.Operator.CT, "Mark%"));
		searchRange.add(new SRange<Integer>("profileAthlete.maxHr", SRange.Operator.GT, 10));
		Criteria criteria = searchRange.buildCriteria(session, Persons.class);

		List<IEntityObject> persons = criteria.list();
		session.close();
		for(IEntityObject person : persons) {
			System.out.println(person.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	private static byte[] loadImageFromMemory(BufferedImage image)
			throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", byteArrayOutputStream);
		return byteArrayOutputStream.toByteArray();
	}

}
