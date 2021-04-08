package com.FoodCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
		int password = Integer.parseInt(request.getParameter("password"));
		String uname = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/food_cart?useSSL=FALSE";
		String query = "Insert into userdata values ('"+ username +"',"+password+")";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try 
		{
			Connection conn = DriverManager.getConnection(url, uname, pass);
			Statement st = conn.createStatement();
			int i = st.executeUpdate(query);
			
			System.out.println("rows affected : "+i);
			response.sendRedirect("FoodMenu.jsp");
			st.close();
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession();
		
		session.setAttribute("username", username);
		
		
		
	}

}
