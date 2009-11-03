package trimatrix.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AcroFormServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public AcroFormServlet() {
		super();		
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// get DAO layer from context
		ServletContext context = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		//DAOLayer daoLayer = DAOLayer.getFromApplicationContext(ctx);
		
		response.setContentType("text/html");
		//response.setContentType("application/pdf");
		PrintWriter out = response.getWriter();

	    String title = "Reading All Request Parameters";
	    out.println(ServletUtils.headWithTitle(title) +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
	                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                "<TR BGCOLOR=\"#FFAD00\">\n" +
	                "<TH>Parameter Name<TH>Parameter Value(s)");
	    Enumeration<String> paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) {
	      String paramName = paramNames.nextElement();
	      System.out.println(paramName);
	      out.println("<TR><TD>" + paramName + "\n<TD>");
	      String[] paramValues = request.getParameterValues(paramName);
	      if (paramValues.length == 1) {
	        String paramValue = paramValues[0];
	        System.out.println(paramValue);
	        if (paramValue.length() == 0)
	          out.print("<I>No Value</I>");
	        else
	          out.print(paramValue);
	      } else {
	        out.println("<UL>");
	        for(int i=0; i<paramValues.length; i++) {
	          out.println("<LI>" + paramValues[i]);
	        }
	        out.println("</UL>");
	      }
	    }
	    out.println("</TABLE>\n</BODY></HTML>");
	    
	    // special handling
	    String speed = request.getParameter("speed");
	    String hr = request.getParameter("hr");
	    String lactate = request.getParameter("lactate");
	    insertValues(speed, hr, lactate);
	    
	    
//	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
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
