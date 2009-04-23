package trimatrix.db;

import org.springframework.context.ApplicationContext;

public class DAOLayer {
	private IUsersDAO usersDAO;
	private IPersonsDAO personsDAO;
		
	public IUsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public IPersonsDAO getPersonsDAO() {
		return personsDAO;
	}

	public void setPersonsDAO(IPersonsDAO personsDAO) {
		this.personsDAO = personsDAO;
	}

	public static DAOLayer getFromApplicationContext(ApplicationContext ctx) {
		return (DAOLayer) ctx.getBean("daoLayer");
	}
}
