package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editdata
 */
@WebServlet("/editdata")
public class editdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//JDBC driver name and database url
			static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			static final String DB_URL="jdbc:mysql://localhost/invoicedb";
			//Database credentials
			static final String USER = "root";
			static final String PASSWORD = "1234";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn = null;
		PreparedStatement stmt = null;
		String total_open_amount = request.getParameter("total_open_amount");
		String notes = request.getParameter("notes");
		String FIELD1 = request.getParameter("FIELD1");
		String jsonString = null;
		String edit_sql;
		int executionstatus = 0;
		List<Response> demolist = new ArrayList<>();
		Response demo = new Response();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);			
			edit_sql = "UPDATE mytable SET total_open_amount="+total_open_amount+", notes='"+notes+"' WHERE FIELD1="+FIELD1+"";
			stmt = conn.prepareStatement(edit_sql);
			
			executionstatus=stmt.executeUpdate();
			if(executionstatus >=1) {
				demo.setExecutionstatus("true");
				demo.setExecutionmessage("data updated successfully");
			}else {
				demo.setExecutionstatus("false");
				demo.setExecutionmessage("updating data failed");
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2) {
			}
			try {
				if(conn!=null)
					conn.close();
				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
