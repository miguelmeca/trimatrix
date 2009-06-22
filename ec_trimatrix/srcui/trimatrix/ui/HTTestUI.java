package trimatrix.ui;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

@CCGenClass (expressionBase="#{d.HTTestUI}")

public class HTTestUI implements Serializable
{    
    protected String m_lactat;
    public String getLactat() { return m_lactat; }
    public void setLactat(String value) { m_lactat = value; }

    protected String m_hr;
    public String getHr() { return m_hr; }
    public void setHr(String value) { m_hr = value; }

    protected String m_speed;
    public String getSpeed() { return m_speed; }
    public void setSpeed(String value) { m_speed = value; }
    
    public void onSubmit(ActionEvent event) {
    	insertValues(m_speed, m_hr, m_lactat);
    }
    
    private void insertValues(String speed, String heartrate, String lactate) {
		Connection conn = null;

        try
        {
            String userName = "trimatrix";
            String password = "trimatrix";
            String url = "jdbc:mysql://localhost/trimatrix";
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");   
            
            Statement d = conn.createStatement();
            d.executeUpdate("DELETE FROM test");
            d.close(); 
            
            Statement i = conn.createStatement();
            String insertStmt = "INSERT INTO test VALUES('" + speed + "','" + heartrate + "','" + lactate + "')";
            System.out.println(insertStmt);
            i.executeUpdate(insertStmt);
            i.close(); 
            Statusbar.outputSuccess("Data written to Database!");
        }
        catch (Exception e)
        {
        	Statusbar.outputMessage ("Cannot connect to database server! " + e.toString());
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
