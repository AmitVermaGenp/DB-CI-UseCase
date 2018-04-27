package org.intillegen;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IntelliGen
 */
public class IntelliGen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntelliGen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try{  
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbURL = "jdbc:sqlserver://13.127.145.136:1433;databaseName=Intelligen;";
			Connection conn =  (Connection) DriverManager.getConnection(dbURL,"intelligenuser", "password" );
			if (conn != null) {
			    System.out.println("Connected");
			}
			
			Statement stmt= conn.createStatement();
			//String query= "insert into user_info( name , email, message) values(  '"+name+"' , '"+email+"' , '"+message+"')";
			String name= request.getParameter("name");
			String email= request.getParameter("email");
			String message = request.getParameter("message");
			
			
			
			String query= "ALTER TABLE user_info ADD phone_number varchar(11)";
			
			int i= stmt.executeUpdate(query);
			conn.close();  
			response.sendRedirect("success.html");
			
			}catch(Exception e){ System.out.println(e);}  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
