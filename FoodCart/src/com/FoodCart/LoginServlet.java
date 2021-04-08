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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		int password = Integer.parseInt(request.getParameter("password"));
		String uname = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/food_cart?useSSL=FALSE";
		String query = "Select UserName from userdata where UserName = '"+username+"' and  Password = " +password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try 
		{
			Connection conn = DriverManager.getConnection(url, uname, pass);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next())
			{
				HttpSession session = request.getSession();
				
				session.setAttribute("username", username);
				
				response.sendRedirect("Order.jsp");
				
			}
			else
			{
				response.sendRedirect("Signup.jsp");
				
			}
			st.close();
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}

}
