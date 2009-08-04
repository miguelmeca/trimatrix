import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class JDBCDemo {
	 public static void main (String[] args)
     {
         Connection conn = null;

         try
         {
             String userName = "trimatrix";
             String password = "trimatrix";
             String url = "jdbc:mysql://localhost/trimatrix";
             Class.forName ("com.mysql.jdbc.Driver").newInstance ();
             conn = DriverManager.getConnection (url, userName, password);
             System.out.println ("Database connection established");
             
             String speed, heartrate, lactate;
             speed = "Speed";
             heartrate = "Heartrate";
             lactate = "Lactate";             
             
             Statement i = conn.createStatement();
             String insertStmt = "INSERT INTO test VALUES('" + speed + "','" + heartrate + "','" + lactate + "')";
             System.out.println(insertStmt);
             i.executeUpdate(insertStmt);
             i.close();
                   
             Statement s = conn.createStatement ();
             s.executeQuery ("SELECT speed, heartrate, lactate FROM test");
             ResultSet rs = s.getResultSet ();
             int count = 0;
             while (rs.next ())
             {
                 String speedVal = rs.getString ("speed");
                 String heartrateVal = rs.getString ("heartrate");
                 String lactateVal = rs.getString ("lactate");
                 System.out.println (
                         "speed = " + speedVal
                         + ", heartrate = " + heartrateVal
                         + ", lactate = " + lactateVal);
                 ++count;
             }
             rs.close ();
             s.close ();
             System.out.println (count + " rows were retrieved");
             
         }
         catch (Exception e)
         {
             System.err.println ("Cannot connect to database server " + e.toString());
         }
         finally
         {
             if (conn != null)
             {
                 try
                 {
                     conn.close ();
                     System.out.println ("Database connection terminated");
                 }
                 catch (Exception e) { /* ignore close errors */ }
             }
         }
     }

}
