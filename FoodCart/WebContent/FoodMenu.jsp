<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.sql.Connection" %>
    <%@ page import = "java.sql.DriverManager" %>
    <%@ page import = "java.sql.Statement"%>
    <%@ page import = "java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		String ordername = request.getParameter("ordername");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String username = session.getAttribute("username").toString();
        if(username != null)
        {
        	String uname = "root";
    		String pass = "root";
    		String url = "jdbc:mysql://localhost:3306/food_cart?useSSL=FALSE";
    		String query = "Insert into cart values ('"+ordername +"',"+quantity+")";
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
                
    			response.sendRedirect("Order.jsp");
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
		


%>

</body>
</html>