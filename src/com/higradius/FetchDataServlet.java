package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class FetchDataServlet
 */
@WebServlet("/FetchDataServlet")
public class FetchDataServlet extends HttpServlet {
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
    public FetchDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		List<Response> demolist = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT name_customer,cust_number,FIELD1,predicted_clear_date,due_in_date,invoice_id,total_open_amount FROM mytable";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Response demo = new Response();
				demo.setName_customer(rs.getString("Name_customer"));
				demo.setCust_number(rs.getString("cust_number"));
				demo.setFIELD1(rs.getString("FIELD1"));
				demo.setPredicted_clear_date(rs.getString("predicted_clear_date"));
				demo.setDue_in_date(rs.getString("due_in_date"));
				demo.setInvoice_id(rs.getString("invoice_id"));
				demo.setTotal_open_amount(rs.getString("total_open_amount"));
				//demo.setNotesdata(rs.getString("notes"));
				demolist.add(demo);
				
			}
			String jsonString = getJSONStringFromObject(demolist);
			response.setContentType("application/json");
			System.out.print(jsonString);
			try {
				response.getWriter().write(jsonString);
			}catch(IOException e){
				e.printStackTrace();
			}
			rs.close();
			stmt.close();
			conn.close();
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
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private String getJSONStringFromObject(List<Response> demolist) {
		// TODO Auto-generated method stub
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json =gson.toJson(demolist);
		System.out.print(json);
		return json;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
