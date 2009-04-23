package trimatrix.utils;

import java.io.FileOutputStream;
import java.sql.Connection;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DBExporter {
	private final static String TABLE = "PERSONS";
	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception{		
		
        Connection jdbcConn = HibernateSessionFactory.getSession().connection();
      
        IDatabaseConnection connection = new DatabaseConnection(jdbcConn);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable(TABLE, "SELECT * FROM " + TABLE);
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("testdata/dumps/DBDump" + TABLE + ".xml"));
        
        jdbcConn.close();

	}

}
