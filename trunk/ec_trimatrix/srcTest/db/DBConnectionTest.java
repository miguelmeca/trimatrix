package db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.hibernate.Hibernate;
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

import trimatrix.db.DAOLayer;
import trimatrix.db.EntitiesHaveLabels;
import trimatrix.db.EntitiesHaveLabelsId;
import trimatrix.db.IEntityDAO;
import trimatrix.db.KRoles;
import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.db.RolesHaveFunctionnodes;
import trimatrix.db.Users;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.Constants;
import trimatrix.utils.ContextStatic;

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
		newUser.setModifiedAt(now);
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
		newUser2.setModifiedAt(now);
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
		newPerson.setNameLast("Reich");
		newPerson.setCreatedAt(now);
		newPerson.setModifiedAt(now);
		personsDAO.merge(newPerson);

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
				Assert.assertEquals("Reich", user.getPerson().getNameLast());
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
		athlete.setRestingHr(46);
		daoLayer.getPersonsDAO().merge(person);		
		System.out.println(athlete.getHeight());
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
