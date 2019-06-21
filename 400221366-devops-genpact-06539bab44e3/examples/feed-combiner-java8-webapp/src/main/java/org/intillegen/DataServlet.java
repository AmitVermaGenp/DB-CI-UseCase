package org.intillegen;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DataServlet
 */
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Data ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		try{  
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbURL = "jdbc:sqlserver://13.234.77.226:1433;databaseName=Intelligen_Dev;";
			Connection conn =  (Connection) DriverManager.getConnection(dbURL,"intelligenuser", "password" );
			if (conn != null) {
			    System.out.println("Connected");
			}
			out.print("<head>" +
					" <title>Intelligen Data</title>" +
  "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>" +
  "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>" +
  "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" +
					"</head>");
			
			out.print("<div class='jumbotron text-xs-center'> "+
			 " <h1 class='display-3'>Your Data is successfully submitted. </h1>"+
			  "<p class='lead'><strong>Thank You! </strong> for showing interest in us..</p>"+
			  "<hr>"+
			  "<p>"+
			   " Having trouble? <a href=''>Contact us</a>"+
			  "</p>"+
			  "<p class='lead'>"+
			   " <a class='btn btn-primary btn-sm' role='button'>Continue</a>"+
			  "</p>"+
			"</div>");
			
			Statement stmt= conn.createStatement();
			String query = "Select TOP 5 * from user_info ORDER BY id desc";
			ResultSet rs = stmt.executeQuery(query);
			out.print("<div class='container'>");
			out.print("<table class='table table-striped'>");
			out.print("<thead>");
			out.print("<tr>");
			out.print("<th>  Id  </th> ");
			out.print("<th>  Name  </th> ");
			out.print("<th>  Email  </th> ");
		//	out.print("<th>  Phone Number </th> ");
			out.print("<th>  Message </th> ");
			out.print("</tr>");
			out.print("</thead>");
			out.print("<tbody>");
			while (rs.next()){
				out.print("<tr>");
				out.print("<td> "+rs.getString("id")+"  </td> ");
				out.print("<td> "+rs.getString("name")+"  </td> ");
				out.print("<td> "+rs.getString("email")+"   </td> ");
		//		out.print("<td> "+rs.getString("phone_number")+"  </td> ");
				out.print("<td> "+rs.getString("message")+"  </td> ");
			
				out.print("</tr>");
			}
			out.print("</tbody>");
			out.print("</table>");
			out.print("</div>");
			
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
