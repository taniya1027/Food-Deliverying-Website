<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
  String username = session.getAttribute("username").toString();
  
 if(username == null || username == "")
 {
	 response.sendRedirect("index.html");
 }


%>
<form action = "FoodMenu.jsp">
	<ul>samosa</ul>
	<input type = "text" name = "ordername"><br>
	
	<input type = "number" name = "quantity"><br>
	<input type = "submit" value = "add" > 
</form>
<form action = "FoodMenu.jsp" >
	<ul>pizza</ul>
	<input type = "text" name = "ordername"><br>
	<input type = "number" name = "quantity"><br>
	<input type = "submit" value = "add"> 
</form>
<form action = "FoodMenu.jsp" >
	<ul>noodles</ul>
	<input type = "text" name = "ordername"><br>
	<input type = "number" name = "quantity"><br>
	<input type = "submit" value = "add"> 
</form>
<form action = "FoodMenu.jsp" >
	<ul>Dahi Papdi</ul>
	<input type = "text" name = "ordername"><br>
	<input type = "number" name = "quantity"><br>
	<input type = "submit" value = "add"> 
	
</form>	
<form action = "PayServlet">

<input type = "submit" value = "Click here to order">
</form>
		

</body> 
</html>