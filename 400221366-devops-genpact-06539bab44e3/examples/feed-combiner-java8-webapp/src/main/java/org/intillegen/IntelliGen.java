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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try{  
			//Class.forName("com.mysql.jdbc.Driver");  
			//Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://13.127.170.204:3306/dummy","dmatuser","dmatuser");  
			//here sonoo is database name, root is username and password  
			//Statement stmt=(Statement) con.createStatement();  
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbURL = "jdbc:sqlserver://52.66.126.95:1433;databaseName=Intelligen;";
			Connection conn =  (Connection) DriverManager.getConnection(dbURL,"intelligenuser", "password" );
			if (conn != null) {
			    System.out.println("Connected");
			}
			
			Statement stmt= conn.createStatement();
			String name= request.getParameter("name");
			String email= request.getParameter("email");
			String message = request.getParameter("message");
			
/*			ResultSet rs=stmt.executeQuery("select * from emp");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); */ 
			
			String query= "insert into user_info( name , email, message) values(  '"+name+"' , '"+email+"' , '"+message+"')";
			int i= stmt.executeUpdate(query);
			if(i>1){
				out.println("done plz check");
			}
			System.err.println("no ");
			conn.close();  
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
