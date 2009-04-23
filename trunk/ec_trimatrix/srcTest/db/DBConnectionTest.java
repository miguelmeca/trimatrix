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
import trimatrix.db.IPersonsDAO;
import trimatrix.db.IUsersDAO;
import trimatrix.db.KRoles;
import trimatrix.db.Persons;
import trimatrix.db.RolesHaveFunctionnodes;
import trimatrix.db.Users;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SFunctionTree;
import trimatrix.utils.ContextStatic;

public class DBConnectionTest {
	private ApplicationContext context = ContextStatic.getInstance();
	private DAOLayer daoLayer = DAOLayer.getFromApplicationContext(context);

	@Before
	public void setUp() throws Exception {

	}

	public void testUsers() {
		Timestamp now = new java.sql.Timestamp((new java.util.Date()).getTime());
		/* 1. Create instance of PersistenceLayer */
		IUsersDAO usersDAO = daoLayer.getUsersDAO();
		IPersonsDAO personsDAO = daoLayer.getPersonsDAO();

		/* 2. Create Test User */
		Users newUser = new Users();
		newUser.setId(UUID.randomUUID().toString());
		newUser.setUserName("Meex");
		newUser.setCreatedAt(now);
		newUser.setModifiedAt(now);
		usersDAO.merge(newUser);

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
		String id = UUID.randomUUID().toString();
		newPerson.setId(id);
		newPerson.setNameLast("Reich");
		newPerson.setCreatedAt(now);
		newPerson.setModifiedAt(now);
		personsDAO.merge(newPerson);

		newUser.setUserHash("test");
		newUser.setPerson(newPerson);
		usersDAO.merge(newUser);

		/* 3. Select Data */
		List<Users> users = (List<Users>) usersDAO.findAll();
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
		IPersonsDAO personsDAO = daoLayer.getPersonsDAO();

		/* 2. Create Test Person */
		Persons newPerson = new Persons();
		String id = UUID.randomUUID().toString();
		newPerson.setId(id);
		BufferedImage img = null;
		img = ImageIO.read(new File("testdata/Tom.jpg"));
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
				"admin", "en");
		System.out.println(functionTree.size());
	}

	@Test
	public void testProgramaticTransactionMngmt() throws Exception {
		/* With Template Pattern */
		// single TransactionTemplate shared amongst all methods in this
		// instance
		final TransactionTemplate transactionTemplate = new TransactionTemplate(
				(HibernateTransactionManager) context
						.getBean("transactionManager"));
		// do Operations
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					Timestamp now = new java.sql.Timestamp(
							(new java.util.Date()).getTime());
					/* 1. Create instance of PersistenceLayer */
					IUsersDAO usersDAO = daoLayer.getUsersDAO();

					/* 2. Create Test User */
					Users newUser = new Users();
					newUser.setId(UUID.randomUUID().toString());
					newUser.setUserName("Meex");
					newUser.setCreatedAt(now);
					newUser.setModifiedAt(now);
					usersDAO.merge(newUser);
					usersDAO.delete(newUser);

					/* 3. Select Data without Lazy Loading possible */
					List<Users> users = (List<Users>) usersDAO.findAll();
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
			IUsersDAO usersDAO = daoLayer.getUsersDAO();

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
