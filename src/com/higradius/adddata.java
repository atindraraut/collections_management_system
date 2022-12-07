package com.higradius;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adddata
 */
@WebServlet("/adddata")
public class adddata extends HttpServlet {
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
    public adddata() {
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
		String cust_number = request.getParameter("cust_number");
		String name_customer = request.getParameter("name_customer");
		String invoice_id = request.getParameter("invoice_id");
		String total_open_amount = request.getParameter("total_open_amount");
		String due_in_date = request.getParameter("due_in_date");
		String notes = request.getParameter("notes");
		String jsonString = null;
		String sql;
		int executionstatus = 0;
		List<Response> demolist = new ArrayList<>();
		Response demo = new Response();
		PrintWriter printwriter = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);			
			sql = "INSERT INTO mytable(cust_number,name_customer,invoice_id,total_open_amount,due_in_date,notes) VALUES(?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,cust_number);
			stmt.setString(2,name_customer);
			stmt.setString(3,invoice_id);
			stmt.setString(4,total_open_amount);
			stmt.setString(5,due_in_date);
			stmt.setString(6,notes);
			executionstatus=stmt.executeUpdate();
			OutputStream os = response.getOutputStream();
			if(executionstatus >=1) {
				demo.setExecutionstatus("true");
				demo.setExecutionmessage("data inserted successfully");
			}else {
				demo.setExecutionstatus("false");
				demo.setExecutionmessage("inserting data failed");
			}printwriter.print(demo.getExecutionstatus());
			printwriter.print(demo.getExecutionmessage());
			
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
