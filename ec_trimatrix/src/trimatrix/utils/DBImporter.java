package trimatrix.utils;

import java.io.File;
import java.sql.Connection;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBImporter {
	private final static String TABLE = "USERS";

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		Connection jdbcConn = HibernateSessionFactory.getSession().connection();

		IDatabaseConnection connection = new DatabaseConnection(jdbcConn);

		IDataSet dataSet = new FlatXmlDataSet(new File("testdata/dumps/DBDump" + TABLE
				+ ".xml"), true);

		DatabaseOperation.INSERT.execute(connection, dataSet);
		
		jdbcConn.close();

	}

}
