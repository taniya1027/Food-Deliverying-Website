package com.FoodCart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("pay servlet working");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
        if(session.getAttribute("username") != null )
        {
        	String uname = "root";
    		String pass = "root";
    		String url = "jdbc:mysql://localhost:3306/food_cart?useSSL=FALSE";
    		String query = "Select * from cart ";
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
    			System.out.println("query worked");
    			int sum = 0;
    			int count = 1;
    			List<String> l = new ArrayList<>();
    			while(rs.next())
    			{
    				
    				l.add(rs.getString(1));
    				String query2 = "Select FoodCost from foodmenu where FoodName = '"+rs.getString(1)+"'";
    				Statement st2 = conn.createStatement();
    				ResultSet rs2 = st2.executeQuery(query2);
    				
    				for(int i = 0; i < l.size();i++)
    				{
    					while(rs2.next())
    					{
    						int x = rs2.getInt(1);
    						System.out.println("x : "+x);
    						sum += x;
    					}
    					
    				}
    			}
    			
    		  
                System.out.println(" Total price = "+sum);
  			
                for(int j = 0; j < l.size(); j++)
                {
                    String query3 = "Delete from cart where ordername = '" + l.get(j) +"'";
                
                    PreparedStatement ps = conn.prepareStatement(query3);
                    int i = ps.executeUpdate();
                    System.out.println("i : "+i);
                }
                
                for(int j = 0; j < l.size(); j++)
                {
                	l.remove(j);
                }
                
               
               session.setAttribute("sum",sum);
                               
                response.sendRedirect("end.jsp");
                
    			st.close();
    			conn.close();
    		} catch (SQLException e)
    		{
    			e.printStackTrace();
    		}

        	
        }
        else
        {
        	response.sendRedirect("Index.html");
        }
		

	}

	
}
