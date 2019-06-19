package org.intillegen;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
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
			String dbURL = "jdbc:sqlserver://52.66.139.121:1433;databaseName=Intelligen_Dev;";
			Connection conn =  (Connection) DriverManager.getConnection(dbURL,"intelligenuser", "password" );
			if (conn != null) {
			    System.out.println("Connected");
			}
			
			Statement stmt= conn.createStatement();
			String name= request.getParameter("name");
			String email= request.getParameter("email");
			String message = request.getParameter("message");
			
			
			String query= "insert into user_info( name , email, message, phone_number) values(  '"+name+"' , '"+email+"' , '"+message+"' , '12345678')";
			//String query= "ALTER TABLE user_info ADD phone_number varchar(11)";
			int i= stmt.executeUpdate(query);
			if(i>1){
				out.println("done plz check");
			}
			System.err.println("no");
			conn.close();  
			RequestDispatcher rd = request.getRequestDispatcher("DataServlet");
			rd.forward(request, response);
			
			
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
